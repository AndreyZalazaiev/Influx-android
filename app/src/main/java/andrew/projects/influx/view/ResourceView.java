package andrew.projects.influx.view;

import java.util.List;

import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.Resource;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ResourceView extends MvpView {
   void getCompanies(List<Company> companies);
   void displayResources(List<Resource> resources);
}
