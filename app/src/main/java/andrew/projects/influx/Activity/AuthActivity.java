package andrew.projects.influx.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import andrew.projects.influx.Presenter.AuthPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.Util.Constants;
import andrew.projects.influx.view.AuthView;
import lombok.SneakyThrows;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class AuthActivity extends MvpAppCompatActivity implements AuthView {

    @InjectPresenter
    AuthPresenter authPresenter;
    String formMode = "Login";

    @ProvidePresenter
    AuthPresenter provideDetailsPresenter() {
        return new AuthPresenter(getSharedPreferences(Constants.prefName, MODE_PRIVATE));
    }

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    @Override
    public void toMainPage() {
        Intent intent = new Intent(AuthActivity.this, NavActivity.class);
        startActivity(intent);
    }

    @Override
    public void userRegistered() {
        onSwitchMode(null);
    }

    public void onSwitchMode(View v) {
        TextView proposalBtn = findViewById(R.id.switchMode);
        EditText emailField = findViewById(R.id.email);
        Button loginBtn = findViewById(R.id.authorize);

        if (formMode.equals("Login")) {
            emailField.setVisibility(View.VISIBLE);
            proposalBtn.setText(getString(R.string.login_now));
            loginBtn.setText(getString(R.string.register));
            formMode = "Register";
        } else {
            emailField.setVisibility(View.INVISIBLE);
            proposalBtn.setText(getString(R.string.new_user_register_now));
            loginBtn.setText(getString(R.string.login));
            formMode = "Login";
        }
    }

    public void onLoginClick(View v) {
        EditText login = findViewById(R.id.login);
        EditText pass = findViewById(R.id.pass);
        EditText email = findViewById(R.id.email);

        if (formMode.equals("Login")) {
            authPresenter.login(login.getText().toString(), pass.getText().toString());
        } else {
            authPresenter.register(login.getText().toString(), pass.getText().toString(), email.getText().toString());
        }
    }
}