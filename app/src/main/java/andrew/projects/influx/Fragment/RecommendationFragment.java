package andrew.projects.influx.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Recommendation;
import andrew.projects.influx.Presenter.RecommendationPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.Util.Constants;
import andrew.projects.influx.view.RecommendationView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

import static android.content.Context.MODE_PRIVATE;

@NoArgsConstructor
public class RecommendationFragment extends MvpAppCompatFragment implements RecommendationView {
    @InjectPresenter
    RecommendationPresenter detailsPresenter;
    private Context ctx;
    private BarChart barChart;
    private TextView recommendationsView;
    private Company company;

    RecommendationFragment(Company company) {
        this.company = company;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            company = (Company) savedInstanceState.getSerializable("company");
        }

        View view = inflater.inflate(R.layout.fragment_recommendations, container, false);
        ctx = getContext();
        barChart = view.findViewById(R.id.chart);
        recommendationsView = view.findViewById(R.id.recommendations);
        detailsPresenter.InitPresenter(company.getId(), barChart, getAuthToken());
        return view;
    }

    public String getAuthToken() {
        SharedPreferences settings = requireContext().getSharedPreferences(Constants.prefName, MODE_PRIVATE);
        return settings.getString("token", "empty");
    }

    @Override
    public void getRecommendations(List<Recommendation> recommendations) {
        recommendations.forEach(r-> recommendationsView.setText(String.format("%s%s", recommendationsView.getText(), r.getText())));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("company",company);
        super.onSaveInstanceState(savedInstanceState);
    }
}
