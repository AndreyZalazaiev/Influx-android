package andrew.projects.influx.Presenter;

import android.util.Log;

import java.util.List;

import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Service.ResourceService;
import andrew.projects.influx.view.ResourceView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class ResourcePresenter extends MvpPresenter<ResourceView> {
    private ResourceService resourceService;
    private int idCompany;
    private String authToken;

    public ResourcePresenter(int idCompany,String authToken) {
        resourceService = new ResourceService();
        this.idCompany=idCompany;
        this.authToken=authToken;
    }

    public void getResourcesList() {
        resourceService.getResources(idCompany,authToken).enqueue(new Callback<List<Resource>>() {
            @Override
            public void onResponse(Call<List<Resource>> call, Response<List<Resource>> response) {

            }

            @Override
            public void onFailure(Call<List<Resource>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }
}
