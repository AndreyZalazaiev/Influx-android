package andrew.projects.influx.Presenter;

import android.util.Log;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Service.CompanyService;
import andrew.projects.influx.Service.ResourceService;
import andrew.projects.influx.view.ResourceView;
import lombok.NoArgsConstructor;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
@NoArgsConstructor
public class ResourcePresenter extends MvpPresenter<ResourceView> {
    private ResourceService resourceService;
    private CompanyService companyService;
    private String authToken;
    private List<Company> loadedCompanies;

    public void init(String authToken) {
        resourceService = new ResourceService();
        companyService = new CompanyService();
        this.authToken = authToken;
        getCompaniesList();

    }

    public void getResourcesList(int idCompany) {
        resourceService.getResources(idCompany, authToken).enqueue(new Callback<List<Resource>>() {
            @Override
            public void onResponse(Call<List<Resource>> call, Response<List<Resource>> response) {
                getViewState().displayResources(response.body());
            }

            @Override
            public void onFailure(Call<List<Resource>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }

    public void getCompaniesList() {
        companyService.getCompanies(authToken).enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                loadedCompanies = response.body();
                getViewState().getCompanies(response.body());
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }

    public void dropdownSelectedItem(String selectedItem) {
        Company company = loadedCompanies.stream().filter(c-> c.getName().equals(selectedItem)).findFirst().orElse(null);
        if(company!=null)
        {
            getResourcesList(company.getId());
        }
    }
}
