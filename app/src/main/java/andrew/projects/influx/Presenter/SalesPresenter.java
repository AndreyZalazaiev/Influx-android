package andrew.projects.influx.Presenter;

import android.util.Log;

import java.util.List;

import andrew.projects.influx.Domain.Sales;
import andrew.projects.influx.Service.SalesService;
import andrew.projects.influx.view.SalesView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class SalesPresenter extends MvpPresenter<SalesView> {

    private SalesService salesService;
    private int idCompany;
    private String authToken;

    public SalesPresenter(int idCompany, String authToken) {
        salesService = new SalesService();
        this.idCompany = idCompany;
        this.authToken = authToken;
    }

    public void getSalesList() {
        salesService.getSales(idCompany, authToken).enqueue(new Callback<List<Sales>>() {
            @Override
            public void onResponse(Call<List<Sales>> call, Response<List<Sales>> response) {

            }

            @Override
            public void onFailure(Call<List<Sales>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }
}
