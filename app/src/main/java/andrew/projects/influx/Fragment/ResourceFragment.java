package andrew.projects.influx.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrew.projects.influx.Presenter.ResourcePresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.view.ResourceView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

@NoArgsConstructor
public class ResourceFragment extends MvpAppCompatFragment implements ResourceView {

    @InjectPresenter
    public ResourcePresenter resourcePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resource, container, false);
    }
}
