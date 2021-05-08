package com.example.recordingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {
    private File[] allFile;
    private TimeAgo timeAgo;

    private onItemClickList onItemClickList;

    public AudioListAdapter(File[] allFile,onItemClickList onItemClickList) {
        this.allFile = allFile;
        this.onItemClickList=onItemClickList;
    }

    @NonNull
    @Override
    public AudioListAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);

        timeAgo = new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioListAdapter.AudioViewHolder holder, int position) {

        holder.list_title.setText(allFile[position].getName());
        holder.list_date.setText(timeAgo.getTimeago(allFile[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return allFile.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView list_image;
        private TextView list_title;
        private TextView list_date;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image = itemView.findViewById(R.id.list_image_view);
            list_title = itemView.findViewById(R.id.list_title);
            list_date = itemView.findViewById(R.id.list_date);

            itemView.setOnClickListener( this);

        }

        @Override
        public void onClick(View view) {

            onItemClickList.onClickListner(allFile[getAdapterPosition()],getAdapterPosition());
        }
    }

    public interface onItemClickList {
        void onClickListner(File file,int position);

    }
}
