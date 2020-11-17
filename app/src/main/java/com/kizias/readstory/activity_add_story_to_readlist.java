package com.kizias.readstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

public class activity_add_story_to_readlist extends AppCompatActivity {

    Fragment fragment;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_to_readlist);
        id = getIntent().getStringExtra("id_story");
        fragment = new fragment_pager_layout_chapter();
        getSupportFragmentManager().beginTransaction().replace(R.id.add_story_to_readlist_frame_layout, fragment).commit();
    }
}