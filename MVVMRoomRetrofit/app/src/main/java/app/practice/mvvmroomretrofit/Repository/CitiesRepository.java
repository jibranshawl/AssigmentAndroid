package app.practice.mvvmroomretrofit.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.practice.mvvmroomretrofit.Dao.CitiesDao;
import app.practice.mvvmroomretrofit.Database.CitiesDatabase;
import app.practice.mvvmroomretrofit.Model.Cities;

public class CitiesRepository {
    private CitiesDatabase database;

    private LiveData<List<Cities>> getALLCities;

    public CitiesRepository(Application application){
           database = CitiesDatabase.getInstance(application);
           getALLCities = database.citiesDao().getALLCities();
    }

    public void insert(List<Cities> citiesList){
        new InsertAsyncTask(database).execute(citiesList);
    }

    public LiveData<List<Cities>> getGetALLCities(){

        return getALLCities;
    }

   static class InsertAsyncTask extends AsyncTask<List<Cities>,Void,Void>{
        private CitiesDao citiesDao;

        InsertAsyncTask(CitiesDatabase citiesDatabase){
            citiesDao = citiesDatabase.citiesDao();
        }

        @Override
        protected Void doInBackground(List<Cities>... lists) {
            citiesDao.insert(lists[0]);
            return null;
        }
    }
}










