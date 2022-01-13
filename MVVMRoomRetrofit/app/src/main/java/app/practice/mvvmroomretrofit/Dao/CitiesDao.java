package app.practice.mvvmroomretrofit.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import app.practice.mvvmroomretrofit.Model.Cities;

@Dao
public interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Cities> citiesList);

    @Query("SELECT * FROM cities")
    LiveData<List<Cities>> getALLCities();

    @Query("DELETE FROM cities")
    void deleteAll();
}
