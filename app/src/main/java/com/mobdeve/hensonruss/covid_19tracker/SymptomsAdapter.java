package com.mobdeve.hensonruss.covid_19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int typeOne = 1;
    private static final int typeTwo = 2;

    private ArrayList<SymptomItem> symptomItems = new ArrayList<SymptomItem>();

    public SymptomsAdapter(ArrayList<SymptomItem> data){
        this.symptomItems = data;
    }

    @Override
    public int getItemViewType(int position) {
        SymptomItem item = symptomItems.get(position);
        int viewType;

        if(item.getType() == SymptomItem.ItemType.typeOne)
            viewType = typeOne;
        else if(item.getType() == SymptomItem.ItemType.typeTwo)
            viewType = typeTwo;
        else
            viewType = -1;

        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == typeOne){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_title, parent);
            return new ViewHolderSymptomTitle(view);
        }
        else if(viewType == typeTwo){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_list_layout, parent);
            return new ViewHolderSymptom(view);
        }
        else
            throw new RuntimeException("Type has to be one or two");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case typeOne:
                initLayoutOne((ViewHolderSymptomTitle) holder, position);
                break;
            case typeTwo:
                initLayoutTwo((ViewHolderSymptom) holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.symptomItems.size();
    }

    public void initLayoutOne(ViewHolderSymptomTitle holder, int pos){
        holder.symptomTitleTv.setText(this.symptomItems.get(pos).getContent());
    }

    public void initLayoutTwo(ViewHolderSymptom holder, int pos){
        holder.symptomTv.setText(this.symptomItems.get(pos).getContent());
    }

    // View holders for the recycler view

    //typeTwo
    static class ViewHolderSymptom extends RecyclerView.ViewHolder{

        private TextView symptomTv;

        public ViewHolderSymptom(@NonNull View itemView) {
            super(itemView);

            this.symptomTv = itemView.findViewById(R.id.symptomTitleTv);
        }
    }

    //typeOne
    static class ViewHolderSymptomTitle extends RecyclerView.ViewHolder{

        private TextView symptomTitleTv;

        public ViewHolderSymptomTitle(@NonNull View itemView) {
            super(itemView);

            this.symptomTitleTv = itemView.findViewById(R.id.symptomTitleTv);
        }
    }
}
