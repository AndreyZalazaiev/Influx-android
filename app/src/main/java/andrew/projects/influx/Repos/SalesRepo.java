package andrew.projects.influx.Repos;

import java.util.List;

import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Domain.Sales;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SalesRepo {

    @GET("/sales/{idCompany}")
    Call<List<Sales>> getSales(@Path("idCompany") int idCompany, @Header("Authorization") String auth);
}
