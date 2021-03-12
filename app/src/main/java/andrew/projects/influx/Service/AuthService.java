package andrew.projects.influx.Service;

import andrew.projects.influx.Util.Constants;
import andrew.projects.influx.Domain.User;
import andrew.projects.influx.Repos.UserRepo;
import lombok.val;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class AuthService {
    Retrofit retrofit;

    public AuthService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public Call<String> login(String login, String pass) {

        val userApi = retrofit.create(UserRepo.class);

        return  userApi.logIn(new User(login, pass));
    }

    public Call<User> getProfile(String token) {
        val userApi = retrofit.create(UserRepo.class);

        return  userApi.getProfile("Bearer "+token);
    }
}
