package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.hourlybatchjob;

import io.nexgrid.bizcoretemplate.domain.access.Access;
import io.nexgrid.bizcoretemplate.domain.access.repository.AccessRepository;
import io.nexgrid.bizcoretemplate.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HourlyStatisticsBatchItemReader implements ItemReader<Access>, ItemStream {

    private final AccessRepository accessRepository;
    private List<String> accessResources;
    private boolean isFirstRun = true;

    @Override
    public Access read()  {
        String day = DateUtil.getDay();
        String hour = DateUtil.getHour();

        if (isFirstRun) {
            log.info("몽고DB에서 Access 컬렉션의 데이터를 가져옵니다.");
            accessResources = accessRepository.findDistinctAccessResource();
            isFirstRun = false;
            log.info("ItemReader가 시작되어 'isFirstRun'을 ${}로 변경합니다.", false);
        }

        accessResources.stream()
                .forEach(
                        accessResource ->
                        {
                            int count = accessRepository.countAccessByAccessResource(accessResource, day, hour);

                        }
                );



        return null;
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        log.info("ItemReader가 종료되어 'isFirstRun'을 ${}로 변경합니다.", true);
        isFirstRun = true;
    }
}
