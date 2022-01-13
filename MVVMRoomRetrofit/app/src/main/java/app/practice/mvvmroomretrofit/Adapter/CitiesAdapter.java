package app.practice.mvvmroomretrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.practice.mvvmroomretrofit.Model.Cities;
import app.practice.mvvmroomretrofit.R;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private Context context;
    private List<Cities> citiesList;

    public CitiesAdapter(Context context, List<Cities> citiesList) {
        Log.d("constructor", String.valueOf(citiesList));
        this.context = context;
        this.citiesList = citiesList;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CitiesViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
            Cities cities = citiesList.get(position);
            holder.population.setText("population: " +String.valueOf(cities.getPopulation()));
            holder.region.setText("region: " +cities.getRegion());
            holder.subregion.setText("subRegion: " +cities.getSubregion());
            holder.flag.setText("Flag: " +cities.getFlag());
    }

    public void getAllCities(List<Cities> citiesList){
        this.citiesList = citiesList;
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public static class CitiesViewHolder extends RecyclerView.ViewHolder{

        TextView population,subregion,region,flag;
        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            population = itemView.findViewById(R.id.population);
            subregion = itemView.findViewById(R.id.subregion);
            region = itemView.findViewById(R.id.region);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
