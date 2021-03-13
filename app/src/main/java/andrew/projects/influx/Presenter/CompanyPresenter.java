package andrew.projects.influx.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.R;
import andrew.projects.influx.Service.CompanyService;
import andrew.projects.influx.view.CompanyView;
import lombok.NoArgsConstructor;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@InjectViewState
@NoArgsConstructor
public class CompanyPresenter extends MvpPresenter<CompanyView> {

    private String authToken;
    private CompanyService companyService = new CompanyService();

    public CompanyPresenter(String authToken) {
        this.authToken = authToken;
        getCompaniesList();
    }


    public void getCompaniesList() {
        companyService.getCompanies(authToken).enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                getViewState().getCompanies(response.body());
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }


}



