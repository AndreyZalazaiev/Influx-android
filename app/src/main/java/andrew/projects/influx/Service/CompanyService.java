package andrew.projects.influx.Service;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Domain.Stats;
import andrew.projects.influx.Repos.CompanyRepo;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;

public class CompanyService {
    Retrofit retrofit;


    public CompanyService() {
        retrofit = RetrofitSingleton.getInstance();
    }

    public Call<List<Company>> getCompanies(String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return companyApi.getCompanies("Bearer " + authToken);
    }

    public Call<List<Stats>> getStats(int idCompany, String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return companyApi.getStats(idCompany, "Bearer " + authToken);
    }

    public Call<List<Recommendation>> getRecommendations(int idCompany, String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return companyApi.getRecommendations(idCompany, "Bearer " + authToken);
    }


}
