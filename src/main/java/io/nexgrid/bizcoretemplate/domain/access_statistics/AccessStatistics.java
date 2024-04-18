package io.nexgrid.bizcoretemplate.domain.access_statistics;

import io.nexgrid.bizcoretemplate.entity.BaseEntity;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.LoginType;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.StatisticsType;
import io.nexgrid.bizcoretemplate.util.DateUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessStatistics extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatisticsType statisticsType;
    private LoginType loginType;
    private String accessResource;
    private String accessorCount;
    private String statisticsStamp; // hourly: yyyyMMddHH, daily: yyyMMdd, monthly: yyyyMM

    public void stampStatistics(StatisticsType statisticsType) {
        switch (statisticsType) {
            case HOURLY -> this.statisticsStamp = DateUtil.getNow();
            case DAILY -> this.statisticsStamp = DateUtil.getDay();
            case MONTHLY -> this.statisticsStamp = DateUtil.getMonth();
        }
    }
}