package andrew.projects.influx.Repos;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Domain.Stats;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CompanyRepo {
    @GET("/company")
    Call<List<Company>> getCompanies(@Header("Authorization") String auth);

    @GET("/recommendation/{idCompany}")
    Call<List<Recommendation>> getRecommendations(@Path("idCompany") int idCompany, @Header("Authorization") String auth);

    @GET("/stats/{idCompany}")
    Call<List<Stats>> getStats(@Path("idCompany") int idCompany, @Header("Authorization") String auth);
}
