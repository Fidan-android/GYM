package com.example.gym;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.RecyclerViewHolder>{

    private JSONArray lessons;
    private Context context;

    public LessonsAdapter(JSONArray lessons, Context context){
        this.lessons = lessons;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        VideoView videoView;
        ImageView imageView;

        public RecyclerViewHolder(View view){
            super(view);

            videoView = view.findViewById(R.id.video_view);
            imageView = view.findViewById(R.id.image_preview);
        }
    }
    @NonNull
    @Override
    public LessonsAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lessons_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonsAdapter.RecyclerViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.lesson1);
        try {
            String url = lessons.getJSONObject(position).getString("url");
            MediaController mediaController = new MediaController(context);
            mediaController.setAnchorView(holder.videoView);
            Uri video = Uri.parse("vnd.youtube:" + url);
            holder.videoView.setMediaController(mediaController);
            holder.videoView.setVideoURI(video);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imageView.setVisibility(View.GONE);
                    holder.videoView.start();
                }
            });
            holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    holder.imageView.setVisibility(View.VISIBLE);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lessons.length();
    }
}
