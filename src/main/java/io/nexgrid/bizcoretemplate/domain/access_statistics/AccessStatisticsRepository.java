package io.nexgrid.bizcoretemplate.domain.access_statistics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessStatisticsRepository extends JpaRepository<AccessStatistics, Long>, AccessStatisticsRepositoryCustom {
}
