package andrew.projects.influx.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrew.projects.influx.Presenter.SalesPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.view.SalesView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

@NoArgsConstructor
public class SalesFragment extends MvpAppCompatFragment implements SalesView {
    @InjectPresenter
    public SalesPresenter salesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }
}
