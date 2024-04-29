package io.nexgrid.bizcoretemplate.domain.access_statistics.repository;

import io.nexgrid.bizcoretemplate.domain.access_statistics.batch.dailybatchjob.HourlyAccessStatisticsDto;

import java.util.List;

public interface AccessStatisticsRepositoryCustom {

    List<HourlyAccessStatisticsDto> getYesterdayAccessStatistics();
}
