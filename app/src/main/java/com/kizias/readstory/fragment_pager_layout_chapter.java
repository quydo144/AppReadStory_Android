package com.kizias.readstory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.kizias.readstory.Adapter.PageAdapter;
import com.kizias.readstory.Adapter.PageChapterAdapter;

public class fragment_pager_layout_chapter extends Fragment {

    SharedPreferences preferences;
    View view;
    ViewPager viewpager_chapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pager_layout_chapter, container, false);
        preferences = getContext().getSharedPreferences("chapter_story", Context.MODE_PRIVATE);
        int count = preferences.getInt("chapter_count", 0);
        viewpager_chapter = view.findViewById(R.id.viewpager_chapter);
        PageChapterAdapter adapter = new PageChapterAdapter(getChildFragmentManager(), count);
        viewpager_chapter.setAdapter(adapter);
        return view;
    }
}