package com.mobdeve.hensonruss.covid_19tracker.ui.dashboard;

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
import com.mobdeve.hensonruss.covid_19tracker.DataRegions;
import com.mobdeve.hensonruss.covid_19tracker.DataSummary;
import com.mobdeve.hensonruss.covid_19tracker.R;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private TextView confirmedNumTv, activeNumTv, recoveredNumTv, deceasedNumTv;

    private TextView topRegionTv, topRegionTv1, topRegionTv2, topRegionTv3, topRegionTv4;
    private TextView topRegionCasesTv, topRegionCasesTv1, topRegionCasesTv2, topRegionCasesTv3, topRegionCasesTv4;

    private Retrofit retrofit;
    private CovidAPIPhilippinesRegions covidAPIPhilippinesRegions;
    private CovidAPIPhilippinesSummary covidAPIPhilippinesSummary;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        this.covidAPIPhilippinesRegions = this.retrofit.create(CovidAPIPhilippinesRegions.class);
        this.covidAPIPhilippinesSummary = this.retrofit.create(CovidAPIPhilippinesSummary.class);

        this.getSummary();

        this.getTopRegions();

        return root;
    }

    private void connectFields(View root){
        this.confirmedNumTv = root.findViewById(R.id.confirmedNumTv);
        this.activeNumTv = root.findViewById(R.id.activeNumTv);
        this.recoveredNumTv = root.findViewById(R.id.recoveredNumTv);
        this.deceasedNumTv = root.findViewById(R.id.deceasedNumTv);

        this.topRegionTv = root.findViewById(R.id.topRegionTv);
        this.topRegionTv1 = root.findViewById(R.id.topRegionTv1);
        this.topRegionTv2 = root.findViewById(R.id.topRegionTv2);
        this.topRegionTv3 = root.findViewById(R.id.topRegionTv3);
        this.topRegionTv4 = root.findViewById(R.id.topRegionTv4);

        this.topRegionCasesTv = root.findViewById(R.id.topRegionCasesTv);
        this.topRegionCasesTv1 = root.findViewById(R.id.topRegionCasesTv1);
        this.topRegionCasesTv2 = root.findViewById(R.id.topRegionCasesTv2);
        this.topRegionCasesTv3 = root.findViewById(R.id.topRegionCasesTv3);
        this.topRegionCasesTv4 = root.findViewById(R.id.topRegionCasesTv4);
    }

    private void getTopRegions(){
        Call<DataRegions> call = this.covidAPIPhilippinesRegions.getTopRegions();

        call.enqueue(new Callback<DataRegions>() {
            @Override
            public void onResponse(Call<DataRegions> call, Response<DataRegions> response) {
                if(!response.isSuccessful()){
                    Log.d("Regions", Integer.toString(response.code()));
                    return;
                }
                DataRegions posts = response.body();

                topRegionTv.setText(posts.getRegionData(0).getRegion().toUpperCase());
                topRegionCasesTv.setText(addComma(posts.getRegionData(0).getCases()));
                topRegionTv1.setText(posts.getRegionData(1).getRegion().toUpperCase());
                topRegionCasesTv1.setText(addComma(posts.getRegionData(1).getCases()));
                topRegionTv2.setText(posts.getRegionData(2).getRegion().toUpperCase());
                topRegionCasesTv2.setText(addComma(posts.getRegionData(2).getCases()));
                topRegionTv3.setText(posts.getRegionData(3).getRegion().toUpperCase());
                topRegionCasesTv3.setText(addComma(posts.getRegionData(3).getCases()));
                topRegionTv4.setText(posts.getRegionData(4).getRegion().toUpperCase());
                topRegionCasesTv4.setText(addComma(posts.getRegionData(4).getCases()));
            }

            @Override
            public void onFailure(Call<DataRegions> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getSummary(){
        Call<DataSummary> call = this.covidAPIPhilippinesSummary.getDataSummary();

        call.enqueue(new Callback<DataSummary>() {
            @Override
            public void onResponse(Call<DataSummary> call, Response<DataSummary> response) {
                if(!response.isSuccessful()){
                    Log.d("Summary", Integer.toString(response.code()));
                    return;
                }
                DataSummary summary = response.body();

                confirmedNumTv.setText(addComma(summary.getSummaryData().getTotal()));
                activeNumTv.setText(addComma(summary.getSummaryData().getActive_cases()));
                recoveredNumTv.setText(addComma(summary.getSummaryData().getRecoveries()));
                deceasedNumTv.setText(addComma(summary.getSummaryData().getDeaths()));
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