package com.mobdeve.hensonruss.covid_19tracker.ui.symptoms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SymptomsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SymptomsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is symptoms fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}