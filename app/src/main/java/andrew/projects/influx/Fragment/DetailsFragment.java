package andrew.projects.influx.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Presenter.DetailsPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.view.DetailsView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@NoArgsConstructor
public class DetailsFragment extends MvpAppCompatFragment implements DetailsView {
    @InjectPresenter
    public DetailsPresenter detailsPresenter;
    private Context ctx;

    DetailsFragment(Company company) {

        Log.v("Info: ", company.toString());
    }

    @ProvidePresenter
    DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        return view;
    }
}
