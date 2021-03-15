package andrew.projects.influx.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Resource;
import andrew.projects.influx.Domain.Sales;
import andrew.projects.influx.Presenter.SalesPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.Util.Constants;
import andrew.projects.influx.view.SalesView;
import lombok.NoArgsConstructor;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

import static android.content.Context.MODE_PRIVATE;

@NoArgsConstructor
public class SalesFragment extends MvpAppCompatFragment implements SalesView {
    @InjectPresenter
    public SalesPresenter salesPresenter;
    private Spinner dropdown;
    private GridLayout sales;
    private SharedPreferences settings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        dropdown = view.findViewById(R.id.dropdown);
        sales = view.findViewById(R.id.sales);
        settings = requireContext().getSharedPreferences(Constants.prefName, MODE_PRIVATE);
        salesPresenter.init(settings.getString("token", "empty"));
        return view;
    }

    @Override
    public void getCompanies(List<Company> companies) {
        if (companies != null) {
            ArrayList<String> options = new ArrayList<>();
            for (Company company : companies
            ) {
                options.add(company.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, options);
            dropdown.setAdapter(adapter);
            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    salesPresenter.dropdownSelectedItem(dropdown.getSelectedItem().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void displayResources(List<Sales> sales) {
        this.sales.removeAllViewsInLayout();
        for (Sales s:sales
        ) {
            View child = View.inflate(getContext(), R.layout.content_card, null);
            TextView title= child.findViewById(R.id.title);
            TextView description =child.findViewById(R.id.description);
            title.setText(s.getIdCompany().toString());
            description.setText(s.getDate().toString());
            this.sales.addView(child);
        }
    }
}
