package com.mobdeve.hensonruss.covid_19tracker.ui.symptoms;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobdeve.hensonruss.covid_19tracker.R;
import com.mobdeve.hensonruss.covid_19tracker.SymptomItem;
import com.mobdeve.hensonruss.covid_19tracker.SymptomsAdapter;

import java.util.ArrayList;

public class SymptomsFragment extends Fragment {

    private SymptomsViewModel symptomsViewModel;
    private TextView symptomsNoteTv;
    private RecyclerView symptomsRv;
    private SymptomsAdapter symptomsAdapter;
    private RecyclerView.LayoutManager manager;

    private ArrayList<SymptomItem> symptomItems;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        symptomsViewModel =
                new ViewModelProvider(this).get(SymptomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_symptoms, container, false);
        final TextView textView = root.findViewById(R.id.text_symptoms);
        symptomsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        symptomsNoteTv = root.findViewById(R.id.symptomsNoteTv);
        symptomsRv = root.findViewById(R.id.symptomsRv);

        loadNote();
        loadSymptomsItems();

        manager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        symptomsRv.setLayoutManager(manager);

        this.symptomsAdapter = new SymptomsAdapter(symptomItems);
        this.symptomsRv.setAdapter(this.symptomsAdapter);

        return root;
    }

    public void loadNote(){
        DocumentReference docRef = db.collection("symptoms").document("note");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String note = document.get("note").toString();
                        symptomsNoteTv.setText(note);
                        Log.d("SYMPTOMS NOTE", "DocumentSnapshot data: " + note);
                    } else {
                        Log.d("SYMPTOMS NOTE", "No such document");
                    }
                } else {
                    Log.d("SYMPTOMS NOTE", "get failed with ", task.getException());
                }
            }
        });
    }

    public void loadSymptomsItems(){
        symptomItems = new ArrayList<>();
        DocumentReference docRef = db.collection("symptoms").document("symptoms list");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        SymptomItem item = new SymptomItem(document.get("mcs title").toString(), SymptomItem.ItemType.typeOne);
                        symptomItems.add(item);
                        SymptomItem item1 = new SymptomItem(document.get("mcs1").toString(), SymptomItem.ItemType.typeTwo);
                        symptomItems.add(item1);
                        SymptomItem item2 = new SymptomItem(document.get("mcs2").toString(), SymptomItem.ItemType.typeTwo);
                        symptomItems.add(item2);
                        SymptomItem item3 = new SymptomItem(document.get("mcs3").toString(), SymptomItem.ItemType.typeTwo);
                        symptomItems.add(item3);

                        symptomsAdapter.notifyDataSetChanged();

                        Log.d("SYMPTOMS NOTE", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("SYMPTOMS NOTE", "No such document");
                    }
                } else {
                    Log.d("SYMPTOMS NOTE", "get failed with ", task.getException());
                }
            }
        });
    }
}