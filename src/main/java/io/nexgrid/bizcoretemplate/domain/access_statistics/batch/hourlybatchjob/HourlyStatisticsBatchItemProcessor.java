package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.hourlybatchjob;

import io.nexgrid.bizcoretemplate.domain.access.repository.AccessRepository;
import io.nexgrid.bizcoretemplate.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class HourlyStatisticsBatchItemProcessor implements ItemProcessor<List<String>, Map<String, Integer>> {

    private final AccessRepository accessRepository;

    @Override
    public Map<String, Integer> process(List<String> items) {
        Map<String, Integer> accessMap = new HashMap<>();
        String day = DateUtil.getDay();
        String hour = DateUtil.getHour();

        items.forEach(
                item -> {
                    Integer count = accessRepository.countByHourly(item, day, hour);
                    System.out.println("path = " + item + ", count = " + count);
                    accessMap.put(item, count);
                }
        );

        return accessMap;
    }

}
