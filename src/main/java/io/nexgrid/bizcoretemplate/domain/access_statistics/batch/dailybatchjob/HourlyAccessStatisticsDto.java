package io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob;

import com.querydsl.core.annotations.QueryProjection;

public record HourlyAccessStatisticsDto(
        String access_resource,
        int access_count,
        String statistics_stamp
) {

    @QueryProjection
    public HourlyAccessStatisticsDto {}

}
