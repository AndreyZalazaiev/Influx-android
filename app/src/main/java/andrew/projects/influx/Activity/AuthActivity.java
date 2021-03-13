package andrew.projects.influx.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import andrew.projects.influx.R;
import andrew.projects.influx.Service.AuthService;
import andrew.projects.influx.Util.Constants;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    AuthService authService = new AuthService();
    SharedPreferences settings;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        settings = getSharedPreferences(Constants.prefName, MODE_PRIVATE);
        if (isLoggedIn()) {
            Log.v("Auth","Automatically logged in");
            toCompanyActivity();
        }

    }

    public void login(String log,String pass) {
        authService.login(log, pass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body()!=null) {
                    settings.edit().putString("token", response.body()).apply();
                    toCompanyActivity();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.v("Response result:", "wrong credentials");
            }
        });
    }

    public boolean isLoggedIn() {
        String token = settings.getString("token", "empty");
        return !token.equals("empty");
    }
    public void onLoginClick(View v) {
        EditText login = findViewById(R.id.login);
        EditText pass = findViewById(R.id.pass);
        login(login.getText().toString(),pass.getText().toString());

    }
    public void toCompanyActivity(){
        Intent intent = new Intent(AuthActivity.this,NavActivity.class);
        startActivity(intent);
    }
}