package com.mobdeve.hensonruss.covid_19tracker.ui.hotline;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HotlineViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public HotlineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("COVID-19 Hotline");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
