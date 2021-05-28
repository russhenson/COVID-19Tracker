package com.mobdeve.hensonruss.covid_19tracker;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataRegions {

    @SerializedName("data")
    private ArrayList<Region> regionData;

    @SerializedName("last_update")
    private String last_update;

    public Region getRegionData(int index){
        return this.regionData.get(index);
    }

    public String getLast_update(){
        return last_update;
    }
}
