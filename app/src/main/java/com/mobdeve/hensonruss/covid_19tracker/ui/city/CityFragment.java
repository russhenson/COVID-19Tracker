package com.mobdeve.hensonruss.covid_19tracker.ui.city;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mobdeve.hensonruss.covid_19tracker.CovidAPIPhilippinesRegions;
import com.mobdeve.hensonruss.covid_19tracker.CovidAPIPhilippinesSummary;
import com.mobdeve.hensonruss.covid_19tracker.DataSummary;
import com.mobdeve.hensonruss.covid_19tracker.R;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityFragment extends Fragment {

    private CityViewModel cityViewModel;

    private TextView cityNameTv, cityConfirmedNumTv, cityActiveNumTv, cityRecoveredNumTv, cityDeceasedNumTv;

    private Retrofit retrofit;
    private CovidAPIPhilippinesSummary covidAPIPhilippinesSummary;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cityViewModel =
                new ViewModelProvider(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_city, container, false);
        final TextView textView = root.findViewById(R.id.text_city);
        cityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        this.connectFields(root);

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://covid19-api-philippines.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.covidAPIPhilippinesSummary = this.retrofit.create(CovidAPIPhilippinesSummary.class);

        this.getSummary();

        return root;
    }

    private void connectFields(View root){
        this.cityNameTv = root.findViewById(R.id.cityNameTv);
        this.cityConfirmedNumTv = root.findViewById(R.id.cityConfirmedNumTv);
        this.cityActiveNumTv = root.findViewById(R.id.cityActiveNumTv);
        this.cityRecoveredNumTv = root.findViewById(R.id.cityRecoveredNumTv);
        this.cityDeceasedNumTv = root.findViewById(R.id.cityDeceasedNumTv);
    }

    private void getSummary(){
        Call<DataSummary> call = this.covidAPIPhilippinesSummary.getNCRSummary();

        call.enqueue(new Callback<DataSummary>() {
            @Override
            public void onResponse(Call<DataSummary> call, Response<DataSummary> response) {
                if(!response.isSuccessful()){
                    Log.d("City", Integer.toString(response.code()));
                    return;
                }
                DataSummary summary = response.body();

                cityNameTv.setText("NCR");

                cityConfirmedNumTv.setText(addComma(summary.getSummaryData().getTotal()));
                cityActiveNumTv.setText(addComma(summary.getSummaryData().getActive_cases()));
                cityRecoveredNumTv.setText(addComma(summary.getSummaryData().getRecoveries()));
                cityDeceasedNumTv.setText(addComma(summary.getSummaryData().getDeaths()));
            }

            @Override
            public void onFailure(Call<DataSummary> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //To add comma to numbers
    private String addComma(int input){
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);

        return format.format((double) input);
    }
}