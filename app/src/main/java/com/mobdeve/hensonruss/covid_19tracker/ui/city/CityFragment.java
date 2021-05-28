package com.mobdeve.hensonruss.covid_19tracker.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mobdeve.hensonruss.covid_19tracker.R;

public class CityFragment extends Fragment {

    private CityViewModel cityViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cityViewModel =
                new ViewModelProvider(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_city, container, false);
        final TextView textView = root.findViewById(R.id.text_city);
        cityViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //add the search

        return root;
    }
}