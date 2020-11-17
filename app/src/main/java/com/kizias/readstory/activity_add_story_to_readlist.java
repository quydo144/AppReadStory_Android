package com.kizias.readstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class activity_add_story_to_readlist extends AppCompatActivity {

    Fragment fragment;
    SharedPreferences preferences;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_to_readlist);
        preferences = getSharedPreferences("chapter_story", MODE_PRIVATE);
        id = getIntent().getStringExtra("id_story");
        fragment = new fragment_pager_layout_chapter();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("story_id", id);
        editor.apply();;
        getSupportFragmentManager().beginTransaction().replace(R.id.add_story_to_readlist_frame_layout, fragment).commit();
    }
}