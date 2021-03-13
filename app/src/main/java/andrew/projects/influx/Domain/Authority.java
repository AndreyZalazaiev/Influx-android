package andrew.projects.influx.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Authority extends BaseEntity {
    String role;
    String authority;
}