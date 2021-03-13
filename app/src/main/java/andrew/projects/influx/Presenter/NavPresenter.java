package andrew.projects.influx.Presenter;


import android.util.Log;

import andrew.projects.influx.Domain.User;
import andrew.projects.influx.Service.AuthService;
import andrew.projects.influx.view.NavView;
import lombok.val;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class NavPresenter extends MvpPresenter<NavView> {
    AuthService loginService = new AuthService();
    String authToken;

    public NavPresenter(String authToken) {
        this.authToken = authToken;
        loadProfileData();
    }

    public void loadProfileData() {
        loginService.getProfile(authToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getViewState().getProfileData(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("Auth","Unable to get user profile");
            }

        });
    }
}
