package com.peterparthimos.personalfinancetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    Context c;

    public MyRecyclerViewAdapter(ArrayList<String> prices, ArrayList<String> dates,
                                 ArrayList<String> names, Context c) {
        this.prices = prices;
        this.dates = dates;
        this.names = names;
        this.c = c;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_list, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textViewName.setText(names.get(position));
        holder.textViewCost.setText(prices.get(position));
        holder.textViewDate.setText(dates.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewCost, textViewDate;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCost = itemView.findViewById(R.id.textViewCost);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }
}
