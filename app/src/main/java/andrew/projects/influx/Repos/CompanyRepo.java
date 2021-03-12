package andrew.projects.influx.Repos;

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
    @POST("/company")
    Call<Company> getCompanies(@Header("Authorization") String auth);

    @POST("/recommendation/{idCompany}")
    Call<Recommendation> getRecommendations(@Path("idCompany") int idCompany, @Header("Authorization") String auth);

    @POST("/stats/{idCompany}")
    Call<Recommendation> getStats(@Path("idCompany") int idCompany, @Header("Authorization") String auth);
}
