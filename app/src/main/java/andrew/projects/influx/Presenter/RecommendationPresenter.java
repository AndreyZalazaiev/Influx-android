package andrew.projects.influx.Presenter;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Domain.Stats;
import andrew.projects.influx.Service.CompanyService;
import andrew.projects.influx.view.RecommendationView;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class RecommendationPresenter extends MvpPresenter<RecommendationView> {
    final ArrayList<String> xAxisLabel = new ArrayList<>();
    private BarChart barChart;
    private CompanyService companyService;
    private String authToken;
    private int idCompany;
    private List<Stats> data;

    public void InitPresenter(int idCompany, BarChart barChart, String authToken) {
        this.companyService = new CompanyService();
        this.barChart = barChart;
        this.authToken = authToken;
        this.idCompany = idCompany;
        getStatsList();
        getRecommendationsList();
    }

    private void getStatsList() {
        companyService.getStats(idCompany, authToken).enqueue(new Callback<List<Stats>>() {
            @Override
            public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                data = response.body();
                fillChart();
            }

            @Override
            public void onFailure(Call<List<Stats>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }

    private void fillChart() {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            entries.add(new BarEntry(i, data.get(i).getCount()));
            xAxisLabel.add(data.get(i).getName());
        }
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setLabelCount(xAxisLabel.size()-1);


        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(Color.parseColor("#05299E"), Color.parseColor("#5E4AE3"), Color.parseColor("#947BD3"), Color.parseColor("#F0A7A0"));
        dataSet.setValueTextColor(Color.BLUE);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void getRecommendationsList() {
        companyService.getRecommendations(idCompany, authToken).enqueue(new Callback<List<Recommendation>>() {
            @Override
            public void onResponse(Call<List<Recommendation>> call, Response<List<Recommendation>> response) {
                getViewState().getRecommendations(response.body());
            }

            @Override
            public void onFailure(Call<List<Recommendation>> call, Throwable t) {
                Log.e("Response result:", "wrong credentials");
            }
        });
    }
}
