package andrew.projects.influx.Presenter;

import android.util.Log;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Sales;
import andrew.projects.influx.Service.CompanyService;
import andrew.projects.influx.Service.ResourceService;
import andrew.projects.influx.Service.SalesService;
import andrew.projects.influx.view.SalesView;
import lombok.NoArgsConstructor;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
@NoArgsConstructor
public class SalesPresenter extends MvpPresenter<SalesView> {

    private SalesService salesService;
    private String authToken;
    private CompanyService companyService;
    private List<Company> loadedCompanies;

    public void init(String authToken) {
        salesService = new SalesService();
        companyService = new CompanyService();
        this.authToken = authToken;
        getCompaniesList();

    }

    public void getSalesList(int idCompany) {
        salesService.getSales(idCompany, authToken).enqueue(new Callback<List<Sales>>() {
            @Override
            public void onResponse(Call<List<Sales>> call, Response<List<Sales>> response) {
                    getViewState().displayResources(response.body());
            }

            @Override
            public void onFailure(Call<List<Sales>> call, Throwable t) {
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
            getSalesList(company.getId());
        }
    }
}
