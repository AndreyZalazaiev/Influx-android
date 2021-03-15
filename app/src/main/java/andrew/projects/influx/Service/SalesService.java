package andrew.projects.influx.Service;

import java.util.List;

import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Domain.Sales;
import andrew.projects.influx.Repos.ResourceRepo;
import andrew.projects.influx.Repos.SalesRepo;
import andrew.projects.influx.Util.Constants;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SalesService {
    Retrofit retrofit;

    public SalesService() {
        retrofit=RetrofitSingleton.getInstance();
    }
    public Call<List<Sales>> getSales(int idCompany, String authToken) {

        val salesApi = retrofit.create(SalesRepo.class);

        return salesApi.getSales(idCompany, "Bearer " + authToken);
    }
}
