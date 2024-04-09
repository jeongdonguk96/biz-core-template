package io.nexgrid.bizcoretemplate.domain.access;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Access {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessor = null;
    private String accessorIp;
    private String accessResource;
    private String accessYear;
    private String accessMonth;
    private String accessDay;
    private String accessHour;
    private LocalDateTime accessTime;
}
