package andrew.projects.influx.Domain;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Authority extends BaseEntity  implements Serializable {
    String role;
    String authority;
}