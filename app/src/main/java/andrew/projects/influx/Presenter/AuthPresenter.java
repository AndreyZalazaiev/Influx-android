package andrew.projects.influx.Presenter;

import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;

import andrew.projects.influx.Domain.User;
import andrew.projects.influx.Service.AuthService;
import andrew.projects.influx.view.AuthView;
import lombok.NoArgsConstructor;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
@NoArgsConstructor
public class AuthPresenter extends MvpPresenter<AuthView> {

    AuthService authService;
    SharedPreferences settings;

    public AuthPresenter(SharedPreferences settings) {
        this.settings = settings;
        authService = new AuthService();
        if (isLoggedIn()) {
            Log.v("Auth", "Automatically logged in");
            getViewState().toMainPage();
        }
    }


    boolean isLoggedIn() {
        String token = settings.getString("token", "empty");
        return !token.equals("empty");
    }


    public void login(String log,String pass) {
        authService.login(log, pass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null) {
                    settings.edit().putString("token", response.body()).apply();
                    getViewState().toMainPage();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("Response result:", "wrong credentials");
            }
        });
    }

    public void register(String login, String pass, String email) {

        authService.register(login, pass,email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getViewState().userRegistered();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("Response result:", " wrong data");
            }
        });
    }
}
