package andrew.projects.influx.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface AuthView  extends MvpView {
    void toMainPage();
    void userRegistered();
}
