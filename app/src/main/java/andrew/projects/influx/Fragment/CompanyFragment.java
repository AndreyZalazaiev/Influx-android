package andrew.projects.influx.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Presenter.CompanyPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.Util.Constants;
import andrew.projects.influx.view.CompanyView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

import static android.content.Context.MODE_PRIVATE;

@NoArgsConstructor
public class CompanyFragment extends MvpAppCompatFragment implements CompanyView {

    @InjectPresenter
    public CompanyPresenter companyPresenter;
    private LinearLayout companiesContainer;
    private Context ctx;

    @ProvidePresenter
    CompanyPresenter provideDetailsPresenter() {
        return new CompanyPresenter(getAuthToken());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        companiesContainer = view.findViewById(R.id.companies);
        ctx = getContext();
        return view;
    }


    @Override
    public void getCompanies(List<Company> companies) {
        for (Company company : companies) {
            View child = View.inflate(ctx, R.layout.company_card_element, null);

            child.setOnClickListener(c -> {
                onCompanyClick(company);
            });

            TextView title = child.findViewById(R.id.title);
            TextView desc = child.findViewById(R.id.description);

            title.setText(company.getName());
            desc.setText(String.format("%s%d ", getString(R.string.amount_of_resources), company.getResources().size()));

            companiesContainer.addView(child);
        }

    }

    private void onCompanyClick(Company company) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new DetailsFragment(company))
                .commit();
    }


    public String getAuthToken() {
        SharedPreferences settings = requireContext().getSharedPreferences(Constants.prefName, MODE_PRIVATE);
        return settings.getString("token", "empty");
    }
}
