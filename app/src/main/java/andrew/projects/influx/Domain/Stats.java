package andrew.projects.influx.Domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private int idResource;
    private String name;
    private long count;
}
