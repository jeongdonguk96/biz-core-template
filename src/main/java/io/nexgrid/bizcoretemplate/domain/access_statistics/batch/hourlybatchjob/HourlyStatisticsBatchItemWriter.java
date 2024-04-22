package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.hourlybatchjob;

import io.nexgrid.bizcoretemplate.domain.access_statistics.AccessStatistics;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.LoginType;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.StatisticsType;
import io.nexgrid.bizcoretemplate.domain.access_statistics.repository.AccessStatisticsRepository;
import io.nexgrid.bizcoretemplate.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class HourlyStatisticsBatchItemWriter implements ItemWriter<Map<String, Integer>> {

    private final AccessStatisticsRepository accessStatisticsRepository;

    @Override
    public void write(Chunk<? extends Map<String, Integer>> chunkMap) {
        List<AccessStatistics> accessStatisticsList = new ArrayList<>();
        System.out.println("chunkMap size = " + chunkMap.size());

        for (Map<String, Integer> map : chunkMap) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                AccessStatistics accessStatistics = AccessStatistics.builder()
                        .statisticsType(StatisticsType.HOURLY)
                        .loginType(LoginType.TOTAL)
                        .accessResource(entry.getKey())
                        .accessorCount(entry.getValue())
                        .statisticsStamp(DateUtil.getNow())
                        .build();

                System.out.println("accessStatistics = " + accessStatistics);
                accessStatisticsList.add(accessStatistics);
            }
        }

        accessStatisticsRepository.saveAll(accessStatisticsList);
    }

}
