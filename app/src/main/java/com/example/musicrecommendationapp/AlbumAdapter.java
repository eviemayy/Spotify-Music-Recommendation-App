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

public class AlbumAdapter extends RecyclerView.Adapter {

    private List<AlbumViewModel> models = Collections.emptyList();
    private AlbumAdapter.OnAlbumItemClickListener onAlbumItemClickListener;

    public interface OnAlbumItemClickListener{
        void onAlbumItemClick(String albumUri, String albumName);
    }

    public AlbumAdapter(AlbumAdapter.OnAlbumItemClickListener onAlbumItemClickListener, List<AlbumViewModel> albumViewModels) {
        this.models = albumViewModels;
        this.onAlbumItemClickListener = onAlbumItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.song_list_item, parent, false);
        return new AlbumItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.d("POSITION", String.valueOf(models.get(position)));
        ((AlbumItemViewHolder) holder).bindData(models.get(position));
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

    class AlbumItemViewHolder extends RecyclerView.ViewHolder {

        private TextView simpleTextView;




        public AlbumItemViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleTextView = (TextView) itemView.findViewById(R.id.simple_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("AlbumAdapter", "onCLICK in ADAPTEER: " + models.get(getAdapterPosition()).getAlbumName());
                    onAlbumItemClickListener.onAlbumItemClick(
                            models.get(getAdapterPosition()).getAlbumUri(),
                            models.get(getAdapterPosition()).getAlbumName()
                    );
                }
            });
        }

        public void bindData(final AlbumViewModel viewModel) {
            simpleTextView.setText(viewModel.getAlbumUri());
        }

    }

}



