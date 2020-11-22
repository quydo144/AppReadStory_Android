package com.kizias.readstory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kizias.readstory.Model.Story;
import com.kizias.readstory.R;
import com.kizias.readstory.activity_story_detail;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<Stories> {

    Context context;
    ArrayList<Story> itemFirst;

    public MyAdapter(Context context, ArrayList<Story> lstFirst) {
        this.itemFirst = lstFirst;
        this.context = context;
    }

    @NonNull
    @Override
    public Stories onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.view_item_story, parent, false);
        return new Stories(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Stories holder, int position) {

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                String id = itemFirst.get(position).getId().get$oid();
                Intent intent = new Intent(context, activity_story_detail.class);
                intent.putExtra("id_story", id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (itemFirst.get(position).getFull() != null){
            if (itemFirst.get(position).getFull()) {
                holder.textview_story_name.setText(itemFirst.get(position).getTitle());
                holder.textview_story_chapter.setText(itemFirst.get(position).getChapterCount() + " chương  [Full]");
                String genre = "";
                for (String s : itemFirst.get(position).getGenre()) {
                    genre = genre + "  " + s;
                }
                holder.textview_story_genre.setText(genre);
                Glide.with(context).load(itemFirst.get(position).getCover()).into(holder.imageview_story_thumb);
            } else {
                holder.textview_story_name.setText(itemFirst.get(position).getTitle());
                holder.textview_story_chapter.setText(itemFirst.get(position).getChapterCount() + " chương");
                String genre = "";
                for (String s : itemFirst.get(position).getGenre()) {
                    genre = genre + "  " + s;
                }
                holder.textview_story_genre.setText(genre);
                Glide.with(context).load(itemFirst.get(position).getCover()).into(holder.imageview_story_thumb);
                holder.imageview_story_full.setVisibility(View.INVISIBLE);
            }
        }
        else {
            holder.textview_story_name.setText(itemFirst.get(position).getTitle());
            holder.textview_story_name.setPadding(0, 70, 0, 0);
            holder.textview_story_name.setTextSize(18);
            Glide.with(context).load(itemFirst.get(position).getCover()).into(holder.imageview_story_thumb);
        }
    }

    @Override
    public int getItemCount() {
        return itemFirst.size();
    }


}
