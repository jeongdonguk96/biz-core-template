package io.nexgrid.bizcoretemplate.domain.access_statistics;

import io.nexgrid.bizcoretemplate.entity.BaseEntity;
import io.nexgrid.bizcoretemplate.enums.LoginType;
import io.nexgrid.bizcoretemplate.enums.StatisticsType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccessStatistics extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LoginType loginType;
    private StatisticsType statisticsType;
    private String accessorCount;
    private String accessResource;
    private String statisticsStamp;
}
