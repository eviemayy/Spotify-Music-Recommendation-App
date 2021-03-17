package com.example.musicrecommendationapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter {

    private List<SongViewModel> models = Collections.emptyList();
    private OnSongItemClickListener onSongItemClickListener;

    public interface OnSongItemClickListener{
        void onSongItemClick(String songUri, String songName);
    }

    public SongAdapter(OnSongItemClickListener onSongItemClickListener, List<SongViewModel> songViewModels) {
        this.models = songViewModels;
        this.onSongItemClickListener = onSongItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.song_list_item, parent, false);
        return new SongItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.d("POSITION", String.valueOf(models.get(position)));
        ((SongItemViewHolder) holder).bindData(models.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("SIZE", String.valueOf(models.size()));
        return models.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.song_list_item;
    }

    class SongItemViewHolder extends RecyclerView.ViewHolder {

        private TextView simpleTextView;


        public SongItemViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleTextView = (TextView) itemView.findViewById(R.id.simple_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("SongAdapter", "onCLICK in ADAPTEER: " + models.get(getAdapterPosition()).getSongName());
                    onSongItemClickListener.onSongItemClick(
                            models.get(getAdapterPosition()).getSongUri(),
                            models.get(getAdapterPosition()).getSongName()
                    );
                }
            });

        }

        public void bindData(final SongViewModel viewModel) {
            simpleTextView.setText(viewModel.getSongUri());
        }

    }
}


