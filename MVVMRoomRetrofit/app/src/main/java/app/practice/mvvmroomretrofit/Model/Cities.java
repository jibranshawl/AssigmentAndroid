package app.practice.mvvmroomretrofit.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity(tableName= "cities")
public class Cities {

    @PrimaryKey(autoGenerate = true)
    private int citiesId;

    @SerializedName("population")
    @ColumnInfo(name = "population")
    private int population;

    @SerializedName("subregion")
    @ColumnInfo(name = "subregion")
    private  String subregion;

    @SerializedName("region")
    @ColumnInfo(name = "region")
    private  String region;

    @SerializedName("flag")
    @ColumnInfo(name = "flag")
    private String flag;

    @SerializedName("borders")
    @ColumnInfo(name="borders")
    private ArrayList<String> borders;

    public Cities(){

    }

    public Cities(int population, String subregion, String region, String flag, ArrayList<String> borders) {
        this.population = population;
        this.subregion = subregion;
        this.region = region;
        this.flag = flag;
        this.borders = borders;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        borders = borders;
    }

    public int getCitiesId() {
        return citiesId;
    }

    public void setCitiesId(int citiesId) {
        this.citiesId = citiesId;
    }


    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "{" +
                "citiesId=" + citiesId +
                ", population=" + population +
                ", subregion='" + subregion + '\'' +
                ", region='" + region + '\'' +
                ", borders=" + borders +
                '}';
    }

}



