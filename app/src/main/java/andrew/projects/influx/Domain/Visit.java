package andrew.projects.influx.Domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit extends BaseEntity {
    private LocalDate date;
    private Integer idCompany;
    private Integer count;
}
