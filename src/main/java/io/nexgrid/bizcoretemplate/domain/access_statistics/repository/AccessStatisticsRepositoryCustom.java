package io.nexgrid.bizcoretemplate.domain.access_statistics.repository;

import io.nexgrid.bizcoretemplate.domain.access_statistics.dto.AccessStatisticsDto;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.StatisticsType;

import java.util.List;

public interface AccessStatisticsRepositoryCustom {

    List<AccessStatisticsDto> getAccessStatisticsByStatisticsType(String date, StatisticsType statisticsType);
}
