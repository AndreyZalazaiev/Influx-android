package andrew.projects.influx.Service;

import java.util.List;

import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Repos.ResourceRepo;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ResourceService {
    Retrofit retrofit;

    public ResourceService() {
        retrofit = RetrofitSingleton.getInstance();
    }

    public Call<List<Resource>> getResources(int idCompany, String authToken) {

        val resourceApi = retrofit.create(ResourceRepo.class);

        return resourceApi.getResources(idCompany, "Bearer " + authToken);
    }

}
