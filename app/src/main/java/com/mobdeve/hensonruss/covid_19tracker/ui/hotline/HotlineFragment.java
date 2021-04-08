package com.mobdeve.hensonruss.covid_19tracker.ui.hotline;

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

public class HotlineFragment extends Fragment {

    private HotlineViewModel hotlineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotlineViewModel =
                new ViewModelProvider(this).get(HotlineViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotline, container, false);
        final TextView textView = root.findViewById(R.id.text_hotline);
        hotlineViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
