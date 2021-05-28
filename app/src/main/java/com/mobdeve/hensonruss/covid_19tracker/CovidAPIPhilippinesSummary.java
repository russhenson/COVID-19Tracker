package com.mobdeve.hensonruss.covid_19tracker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPIPhilippinesSummary {

    @GET("summary")
    Call<DataSummary> getDataSummary();
}
