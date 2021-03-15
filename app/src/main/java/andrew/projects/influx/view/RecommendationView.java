package andrew.projects.influx.view;

import java.util.ArrayList;
import java.util.List;

import andrew.projects.influx.Domain.Recommendation;
import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface RecommendationView extends MvpView {
    void getRecommendations(List<Recommendation> recommendations);
}
