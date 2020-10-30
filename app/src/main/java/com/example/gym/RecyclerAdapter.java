package com.example.gym;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    ArrayList<ImageView> imageViews;
    ArrayList<TextView> textViews;
    Context context;
    Activity activity;

    public RecyclerAdapter(ArrayList<ImageView> images, ArrayList<TextView> texts, Context context, Activity activity) {
        imageViews = images;
        textViews = texts;
        this.activity = activity;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public RecyclerViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.text);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position){
        holder.imageView = imageViews.get(position);
        holder.textView = textViews.get(position);
    }

    @Override
    public int getItemCount() {
        return imageViews.size();
    }
}
