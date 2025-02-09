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

public class ArtistAdapter extends RecyclerView.Adapter {

    private List<ArtistViewModel> models = Collections.emptyList();
    private OnArtistItemClickListener onArtistItemClickListener;

    public interface OnArtistItemClickListener {
        void onArtistItemClick(String artistUri, String artistName);
    }

    public ArtistAdapter(OnArtistItemClickListener onArtistItemClickListener, List<ArtistViewModel> artistViewModels) {
        this.models = artistViewModels;
        this.onArtistItemClickListener = onArtistItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.song_list_item, parent, false);
        return new ArtestItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.d("POSITION", String.valueOf(models.get(position)));
        ((ArtestItemViewHolder) holder).bindData(models.get(position));
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

    class ArtestItemViewHolder extends RecyclerView.ViewHolder {

        private TextView simpleTextView;
        private TextView simpleTextView2;

        private TextView simpleTextView3;

        private TextView simpleTextView4;

        private TextView simpleTextView5;



        public ArtestItemViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleTextView = (TextView) itemView.findViewById(R.id.simple_text);
            /*simpleTextView2 = (TextView) itemView.findViewById(R.id.tv_pop);
            simpleTextView3 = (TextView) itemView.findViewById(R.id.tv_low_temp);
            simpleTextView4 = (TextView) itemView.findViewById(R.id.tv_high_temp);
            simpleTextView5 = (TextView) itemView.findViewById(R.id.tv_time);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("SongAdapter", "onCLICK in ADAPTEER: " + models.get(getAdapterPosition()).getArtistUri());
                    onArtistItemClickListener.onArtistItemClick(
                            models.get(getAdapterPosition()).getArtistUri(),
                            models.get(getAdapterPosition()).getArtistName()
                    );
                }
            });

        }


        public void bindData(final ArtistViewModel viewModel) {
            simpleTextView.setText(viewModel.getArtistName());

        }

    }
}


