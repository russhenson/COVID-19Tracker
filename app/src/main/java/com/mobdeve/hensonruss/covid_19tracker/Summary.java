package com.mobdeve.hensonruss.covid_19tracker;

import com.google.gson.annotations.SerializedName;

public class Summary {
    @SerializedName("total")
    private int total;

    @SerializedName("recoveries")
    private int recoveries;

    @SerializedName("deaths")
    private int deaths;

    @SerializedName("active_cases")
    private int active_cases;

    @SerializedName("fatality_rate")
    private String fatality_rate;

    @SerializedName("recovery_rate")
    private String recovery_rate;

    public int getTotal() {
        return total;
    }

    public int getRecoveries() {
        return recoveries;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getActive_cases() {
        return active_cases;
    }

    public String getFatality_rate() {
        return fatality_rate;
    }

    public String getRecovery_rate() {
        return recovery_rate;
    }
}
