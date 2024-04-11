package io.nexgrid.bizcoretemplate.domain.access;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@ToString
@Document(collection = "access")
@NoArgsConstructor
@AllArgsConstructor
public class Access {
    @Id
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
