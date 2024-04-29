package io.nexgrid.bizcoretemplate.domain.access_statistics;

import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.LoginType;
import io.nexgrid.bizcoretemplate.domain.access_statistics.enums.StatisticsType;
import io.nexgrid.bizcoretemplate.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessStatistics extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatisticsType statisticsType;
    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    private String accessResource;
    private int accessCount;
    private String statisticsStamp; // hourly: getNow(), daily: getDay(), monthly: getMonth()
}