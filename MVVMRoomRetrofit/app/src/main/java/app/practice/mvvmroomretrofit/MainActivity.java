package app.practice.mvvmroomretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.practice.mvvmroomretrofit.Adapter.CitiesAdapter;
import app.practice.mvvmroomretrofit.Model.Cities;
import app.practice.mvvmroomretrofit.Network.Api;
import app.practice.mvvmroomretrofit.Repository.CitiesRepository;
import app.practice.mvvmroomretrofit.ViewModel.CitiesViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private CitiesViewModel citiesViewModel;
    private RecyclerView recyclerView;
    private static final String URL_DATA= "https://restcountries.com/v3.1/";
    private CitiesAdapter citiesAdapter;
    private List<Cities> citiesList;
    private CitiesRepository citiesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        citiesRepository = new CitiesRepository(getApplication());
        citiesList = new ArrayList<>();
        citiesAdapter = new CitiesAdapter(MainActivity.this,citiesList);
        citiesViewModel = new ViewModelProvider(this).get(CitiesViewModel.class);

        networkRequest();
        citiesViewModel.getGetAllCitis().observe(this, new Observer<List<Cities>>() {
            @Override
            public void onChanged(List<Cities> citiesList) {
                Toast.makeText(MainActivity.this,"Working fine",Toast.LENGTH_SHORT).show();
                Log.d("onChanged", String.valueOf(citiesList));
                recyclerView.setAdapter(citiesAdapter);

                citiesAdapter.getAllCities(citiesList);
            }
        });

    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL_DATA)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        Api api = retrofit.create(Api.class);
        Call<List<Cities>> call = api.getAllCities();
        call.enqueue(new Callback<List<Cities>>() {
            @Override
            public void onResponse(Call<List<Cities>> call, Response<List<Cities>> response) {

                if(response.body() !=null) {
                    Log.d("onResponse", String.valueOf(response.body()));
                    citiesRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Cities>> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }
}