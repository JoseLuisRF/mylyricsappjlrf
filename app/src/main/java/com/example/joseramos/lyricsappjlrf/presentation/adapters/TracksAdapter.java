package com.example.joseramos.lyricsappjlrf.presentation.adapters;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joseramos.lyricsappjlrf.R;
import com.example.joseramos.lyricsappjlrf.databinding.ItemAlbumTrackBinding;
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel;

import java.util.ArrayList;
import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TrackViewHolder> {

    private List<TrackModel> data = new ArrayList<>();

    public TracksAdapter(List<TrackModel> tracks) {
        this.data = tracks;
    }

    private TracksAdapterListener listener;

    public interface TracksAdapterListener<T> {
        void onItemClick(View view, int position,  T item);
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlbumTrackBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_album_track,
                parent,
                false);

        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, final int position) {
        final TrackModel trackModel = data.get(position);
        holder.binding.setModel(trackModel);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, position, trackModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setTrackAdapterListener(TracksAdapterListener listener) {
        this.listener = listener;
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {

        ItemAlbumTrackBinding binding;

        public TrackViewHolder(ItemAlbumTrackBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
