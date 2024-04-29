package io.nexgrid.bizcoretemplate.domain.access_statistics.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.nexgrid.bizcoretemplate.domain.access_statistics.dto.AccessStatisticsDto;
import io.nexgrid.bizcoretemplate.domain.access_statistics.dto.QAccessStatisticsDto;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.StatisticsType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static io.nexgrid.bizcoretemplate.domain.access_statistics.QAccessStatistics.accessStatistics;

@RequiredArgsConstructor
public class AccessStatisticsRepositoryImpl implements AccessStatisticsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AccessStatisticsDto> getAccessStatisticsByStatisticsType(String date, StatisticsType statisticsType) {
        return jpaQueryFactory
                .select(new QAccessStatisticsDto(
                        accessStatistics.accessResource,
                        accessStatistics.accessCount,
                        accessStatistics.statisticsStamp))
                .from(accessStatistics)
                .where(
                        determineDate(date, statisticsType)
                        .and(accessStatistics.statisticsType
                                .eq(statisticsType))
                )
                .fetch();
    }

    // StatisticsType에 따라 작업할 날짜를 정한다.
    private BooleanExpression determineDate(String date, StatisticsType type) {
        if (type.equals(StatisticsType.HOURLY)) {
            return accessStatistics.statisticsStamp.substring(0, 8).eq(date);
        } else if(type.equals(StatisticsType.DAILY)){
            return accessStatistics.statisticsStamp.substring(0, 6).eq(date);
        }

        return null;
    }

}
