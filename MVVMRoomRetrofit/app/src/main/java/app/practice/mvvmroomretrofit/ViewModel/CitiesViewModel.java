package app.practice.mvvmroomretrofit.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.practice.mvvmroomretrofit.Model.Cities;
import app.practice.mvvmroomretrofit.Repository.CitiesRepository;

public class CitiesViewModel extends AndroidViewModel {

    private CitiesRepository citiesRepository;
    private LiveData<List<Cities>> getAllCities;

    public CitiesViewModel(@NonNull Application application) {
        super(application);
        citiesRepository = new CitiesRepository(application);
        getAllCities = citiesRepository.getGetALLCities();
    }

    public void insert(List<Cities> list){

        Log.d("getrepo1", String.valueOf(list));
        citiesRepository.insert(list);
    }

    public LiveData<List<Cities>> getGetAllCitis(){

        Log.d("getrepo2", String.valueOf(getAllCities));
        return getAllCities;
    }
}
