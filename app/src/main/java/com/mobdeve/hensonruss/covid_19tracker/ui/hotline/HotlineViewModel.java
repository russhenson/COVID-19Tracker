package com.mobdeve.hensonruss.covid_19tracker.ui.hotline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HotlineViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public HotlineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hotline fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
