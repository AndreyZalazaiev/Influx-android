package andrew.projects.influx.Repos;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CompanyRepo {
    @GET("/company")
    Call<List<Company>> getCompanies(@Header("Authorization") String auth);

    @GET("/recommendation/{idCompany}")
    Call<Recommendation> getRecommendations(@Path("idCompany") int idCompany, @Header("Authorization") String auth);

    @GET("/stats/{idCompany}")
    Call<Recommendation> getStats(@Path("idCompany") int idCompany, @Header("Authorization") String auth);
}
