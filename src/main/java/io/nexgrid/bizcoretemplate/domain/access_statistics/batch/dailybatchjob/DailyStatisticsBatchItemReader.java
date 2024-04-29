package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob;

import io.nexgrid.bizcoretemplate.domain.access_statistics.repository.AccessStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DailyStatisticsBatchItemReader implements ItemReader<List<HourlyAccessStatisticsDto>> {

    private final AccessStatisticsRepository accessStatisticsRepository;
    private boolean isRun = true;

    @Override
    public List<HourlyAccessStatisticsDto> read() {
        if (isRun) {
            List<HourlyAccessStatisticsDto> yesterdayAccessStatistics = accessStatisticsRepository.getYesterdayAccessStatistics();
            log.info("마리아DB에서 AccessStatistics 엔티티에서 전날 일별 통계 데이터를 가져옵니다. yesterdayAccessStatistics distinct size = {}", yesterdayAccessStatistics.size());
            isRun = false;

            return yesterdayAccessStatistics;
        } else {
            isRun = true;
            return null;
        }


    }
}