package com.mobdeve.hensonruss.covid_19tracker;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataSummary {
    @SerializedName("data")
    private Summary summaryData;

    @SerializedName("last_update")
    private String last_update;

    public Summary getSummaryData(){
        return this.summaryData;
    }

    public String getLast_update(){
        return last_update;
    }
}
