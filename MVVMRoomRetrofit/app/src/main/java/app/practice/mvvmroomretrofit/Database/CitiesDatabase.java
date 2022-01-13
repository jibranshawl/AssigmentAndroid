package app.practice.mvvmroomretrofit.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import app.practice.mvvmroomretrofit.Dao.CitiesDao;

import app.practice.mvvmroomretrofit.Model.Cities;
import app.practice.mvvmroomretrofit.Model.Converters;

@Database(entities = {Cities.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class CitiesDatabase extends RoomDatabase {
    private static final String DATABSE_NAME="CitiesDatabase";

    public abstract CitiesDao citiesDao();

    private static volatile CitiesDatabase INSTANCE;

    public static CitiesDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (CitiesDatabase.class) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,CitiesDatabase.class,DATABSE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

  static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };
    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private CitiesDao citiesDao;
        PopulateAsyncTask(CitiesDatabase citiesDatabase){
            citiesDao = citiesDatabase.citiesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            citiesDao.deleteAll();
            return null;
        }
    }

}
