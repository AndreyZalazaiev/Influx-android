package andrew.projects.influx.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import andrew.projects.influx.Domain.Authority;
import andrew.projects.influx.Domain.Company;
import andrew.projects.influx.Domain.User;
import andrew.projects.influx.Fragment.CompanyFragment;
import andrew.projects.influx.Presenter.NavPresenter;
import andrew.projects.influx.R;
import andrew.projects.influx.view.NavView;
import lombok.val;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class NavActivity extends MvpAppCompatActivity implements NavView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    NavPresenter navPresenter;
    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences settings;
    private User user;

    @ProvidePresenter
    NavPresenter provideDetailsPresenter() {
        settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        return new NavPresenter(settings.getString("token", "empty"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
           user = (User) savedInstanceState.getSerializable("user");
        }
        setContentView(R.layout.activity_nav);
        settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_company)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setNavigationViewListener();

        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_company));

    }

    public void getProfileData(User u) {
        TextView login = findViewById(R.id.Login);
        TextView email = findViewById(R.id.Email);
        TextView role = findViewById(R.id.Role);
        this.user=u;

        login.setText(user.getUsername());
        email.setText(user.getEmail());
        role.setText(user.getAuthorities().stream().findFirst().orElse(new Authority()).getAuthority());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        val actionBar = getSupportActionBar();
        switch (item.getItemId()) {

            case R.id.nav_log_out: {
                settings.edit().remove("token").commit();
                switchActivity(AuthActivity.class);
                break;
            }
            case R.id.nav_company: {
                actionBar.setTitle(getString(R.string.company));
                actionBar.setIcon(R.drawable.ic_domain_black_24dp);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new CompanyFragment())
                        .commit();
                break;
            }

        }
        return true;
    }

    public void switchActivity(Class activity) {
        Intent intent = new Intent(NavActivity.this, activity);
        startActivity(intent);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("user",user);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onBackPressed() {

    }
}



