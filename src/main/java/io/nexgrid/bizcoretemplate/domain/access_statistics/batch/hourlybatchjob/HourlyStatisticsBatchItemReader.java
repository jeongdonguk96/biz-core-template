package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.hourlybatchjob;

import io.nexgrid.bizcoretemplate.domain.access.repository.AccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HourlyStatisticsBatchItemReader implements ItemReader<List<String>> {

    private final AccessRepository accessRepository;
    private boolean isRun = true;

    @Override
    public List<String> read()  {
        if (isRun) {
            List<String> accessResource = accessRepository.findDistinctAccessResource();
            log.info("몽고DB에서 Access 컬렉션의 accessResource를 가져옵니다. accessResource distinct size = ${}", accessResource.size());
            isRun = false;

            return accessResource;
        } else {
            isRun = true;
            return null;
        }
    }

}
