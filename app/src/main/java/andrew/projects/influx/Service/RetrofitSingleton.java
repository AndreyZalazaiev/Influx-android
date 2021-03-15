package andrew.projects.influx.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import andrew.projects.influx.Util.Constants;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static retrofit2.Retrofit instance;

    public static retrofit2.Retrofit getInstance() {
        if (instance == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new DateTypeDeserializer())
                    .create();
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return instance;
        }
        return instance;
    }


}
