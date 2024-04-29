package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class DailyStatisticsBatchItemProcessor implements ItemProcessor<List<HourlyAccessStatisticsDto>, Map<String, Integer>> {

    @Override
    public Map<String, Integer> process(List<HourlyAccessStatisticsDto> items) {
        return items.stream()
                .collect(Collectors.toMap(
                        HourlyAccessStatisticsDto::access_resource, // 키
                        HourlyAccessStatisticsDto::access_count,  // 밸류
                        Integer::sum                                // 동일한 키가 있을 경우 밸류 합산
                ));
    }
}

//        Map<String, Integer> accessStatisticeMap = new HashMap<>();
//        for (HourlyAccessStatisticsDto item : items) {
//                String resource = item.access_resource();
//                int count = item.accessor_count();
//
//                if (accessStatisticeMap.containsKey(resource)) {
//                int totalAccessCount = accessStatisticeMap.get(resource);
//                totalAccessCount += count;
//                accessStatisticeMap.put(resource, totalAccessCount);
//                } else {
//                accessStatisticeMap.put(resource, count);
//                }
//                }
//
//                return accessStatisticeMap;