package andrew.projects.influx.Domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Resource extends BaseEntity {
    private String name;
    private String price;
    private Integer idCompany;
    private List<Sales> sales;
