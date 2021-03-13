package andrew.projects.influx.view;

import andrew.projects.influx.Domain.User;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface NavView extends MvpView {
    void getProfileData(User u);
}
