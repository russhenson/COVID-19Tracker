package com.mobdeve.hensonruss.covid_19tracker.ui.hotline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobdeve.hensonruss.covid_19tracker.MainActivity;
import com.mobdeve.hensonruss.covid_19tracker.NavBar;
import com.mobdeve.hensonruss.covid_19tracker.R;

public class HotlineFragment extends Fragment {

    private HotlineViewModel hotlineViewModel;

    private TextView advisoryTv;
    private TextView hotlineNumTv;
    private TextView hotlineNumTv1;
    private TextView hotlineNumTv2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotlineViewModel = new ViewModelProvider(this).get(HotlineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotline, container, false);
        final TextView textView = root.findViewById(R.id.text_hotline);
        hotlineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        advisoryTv = root.findViewById(R.id.advisoryTv);
        hotlineNumTv = root.findViewById(R.id.hotlineNumTv);
        hotlineNumTv1 = root.findViewById(R.id.hotlineNumTv1);
        hotlineNumTv2 = root.findViewById(R.id.hotlineNumTv2);

        // Retrieves data from firebase then set text to the textviews
        loadAdvisory();
        loadMainHotline();
        loadCompleteHotline();
        loadSecondaryHotline();

        return root;
    }

    public void loadAdvisory() {

        DocumentReference docRef = db.collection("hotline").document("advisory");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String advisory = document.get("advisory").toString().replaceAll("\\\\n", "\n");
                        advisoryTv.setText(advisory);
                        Log.d("HOTLINE ADVISORY", "DocumentSnapshot data: " + advisory);
                    } else {
                        Log.d("HOTLINE ADVISORY", "No such document");
                    }
                } else {
                    Log.d("HOTLINE ADVISORY", "get failed with ", task.getException());
                }
            }
        });

    }
    public void loadMainHotline(){
        DocumentReference docRef = db.collection("hotline").document("main hotline");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String number = document.get("number").toString();
                        hotlineNumTv.setText(number);
                        Log.d("HOTLINE ADVISORY", "DocumentSnapshot data: " + number);
                    } else {
                        Log.d("HOTLINE ADVISORY", "No such document");
                    }
                } else {
                    Log.d("HOTLINE ADVISORY", "get failed with ", task.getException());
                }
            }
        });
    }
    public void loadCompleteHotline(){
        DocumentReference docRef = db.collection("hotline").document("complete hotline");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String number = document.get("number").toString();
                        hotlineNumTv1.setText(number);
                        Log.d("HOTLINE", "DocumentSnapshot data: " + number);
                    } else {
                        Log.d("HOTLINE", "No such document");
                    }
                } else {
                    Log.d("HOTLINE", "get failed with ", task.getException());
                }
            }
        });
    }
    public void loadSecondaryHotline(){
        DocumentReference docRef = db.collection("hotline").document("secondary hotline");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String number = document.get("number").toString();
                        hotlineNumTv2.setText(number);
                        Log.d("HOTLINE", "DocumentSnapshot data: " + number);
                    } else {
                        Log.d("HOTLINE", "No such document");
                    }
                } else {
                    Log.d("HOTLINE", "get failed with ", task.getException());
                }
            }
        });
    }
}
