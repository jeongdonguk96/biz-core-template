package io.nexgrid.bizcoretemplate.domain.access_statistics.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob.HourlyAccessStatisticsDto;
import io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob.QHourlyAccessStatisticsDto;
import io.nexgrid.bizcoretemplate.util.DateUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static io.nexgrid.bizcoretemplate.domain.access_statistics.QAccessStatistics.accessStatistics;

@RequiredArgsConstructor
public class AccessStatisticsRepositoryImpl implements AccessStatisticsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<HourlyAccessStatisticsDto> getYesterdayAccessStatistics() {
        return jpaQueryFactory
                .select(new QHourlyAccessStatisticsDto(
                        accessStatistics.accessResource,
                        accessStatistics.accessCount,
                        accessStatistics.statisticsStamp))
                .from(accessStatistics)
                .where(accessStatistics.statisticsStamp.substring(0, 8)
                        .eq(DateUtil.getYesterday()))
                .fetch();
    }

}
