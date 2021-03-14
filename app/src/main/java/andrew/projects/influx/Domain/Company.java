package andrew.projects.influx.Domain;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company extends BaseEntity {
    private Integer idUser;
    private String name;
    private Set<Recommendation> recommendations;
    private Set<Resource> resources;
    private Set<Sales> sales;


}