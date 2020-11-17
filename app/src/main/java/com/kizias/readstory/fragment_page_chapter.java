package com.kizias.readstory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kizias.readstory.Adapter.PageChapterAdapter;

public class fragment_page_chapter extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewPager viewpager_chapter;

        viewpager_chapter = (ViewPager) inflater.inflate(R.layout.fragment_pager_layout_chapter, container, false).findViewById(R.id.viewpager_chapter);
        PageChapterAdapter pageAdapter = new PageChapterAdapter(getFragmentManager());
        viewpager_chapter.setAdapter(pageAdapter);
        viewpager_chapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
            }
        });

        return inflater.inflate(R.layout.fragment_page_chapter, container, false);
    }
}