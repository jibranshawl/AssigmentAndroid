package app.practice.mvvmroomretrofit.Network;

import java.util.List;

import app.practice.mvvmroomretrofit.Model.Cities;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("region/asia")
    Call<List<Cities>> getAllCities();
}
