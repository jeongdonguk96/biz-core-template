package io.nexgrid.bizcoretemplate.domain.access_statistics.dto;

import com.querydsl.core.annotations.QueryProjection;

public record AccessStatisticsDto(
        String access_resource,
        int access_count,
        String statistics_stamp
) {

    @QueryProjection
    public AccessStatisticsDto {}

}
