package com.mobdeve.hensonruss.covid_19tracker.ui.city;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("City / Region Cases");
    }

    public LiveData<String> getText() {
        return mText;
    }
}