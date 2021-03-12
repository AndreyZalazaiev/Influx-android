package andrew.projects.influx.Repos;


import andrew.projects.influx.Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserRepo {
    @POST("/login")
    Call<String> logIn(@Body User user);
    @GET("/profile")
    Call<User> getProfile(@Header("Authorization") String auth);
}
