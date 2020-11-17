package com.kizias.readstory;

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

    View view;
    ViewPager viewpager_chapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pager_layout_chapter, container, false);

        return view;
    }
}