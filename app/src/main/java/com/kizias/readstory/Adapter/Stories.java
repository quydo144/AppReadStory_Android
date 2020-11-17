package com.kizias.readstory.Adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kizias.readstory.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Stories extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;
    TextView textview_story_name, textview_story_chapter, textview_story_genre;
    ImageView imageview_story_full;
    CircleImageView imageview_story_thumb;

    public Stories(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        textview_story_name = (TextView) itemView.findViewById(R.id.textview_story_name);
        textview_story_chapter = (TextView) itemView.findViewById(R.id.textview_story_chapter);
        textview_story_genre = (TextView) itemView.findViewById(R.id.textview_story_genre);
        imageview_story_thumb = (CircleImageView) itemView.findViewById(R.id.imageview_story_thumb);
        imageview_story_full = (ImageView) itemView.findViewById(R.id.imageview_story_full);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
