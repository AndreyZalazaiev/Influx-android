package andrew.projects.influx.Repos;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Resource;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ResourceRepo {

    @GET("/resource/{idCompany}")
    Call<List<Resource>> getResources(@Path("idCompany") int idCompany,@Header("Authorization") String auth);
}
