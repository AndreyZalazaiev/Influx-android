package andrew.projects.influx.Domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation extends BaseEntity {
    private Integer idCompany;
    private String text;
    private LocalDateTime date;
    private Integer period;

}
