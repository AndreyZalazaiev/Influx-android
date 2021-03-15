package andrew.projects.influx.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Singleton;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Domain.Stats;
import andrew.projects.influx.Repos.CompanyRepo;
import andrew.projects.influx.Util.Constants;
import dagger.Component;
import dagger.Provides;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class CompanyService {
    Retrofit retrofit;


    public CompanyService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public Call<List<Company>> getCompanies(String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return  companyApi.getCompanies("Bearer "+authToken);
    }

    public Call<List<Stats>> getStats(int idCompany,String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return  companyApi.getStats(idCompany,"Bearer "+authToken);
    }

    public Call<List<Recommendation>> getRecommendations(int idCompany, String authToken) {

        val companyApi = retrofit.create(CompanyRepo.class);

        return  companyApi.getRecommendations(idCompany,"Bearer "+authToken);
    }


    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,new DateTypeDeserializer())
            .create();

}
