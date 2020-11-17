package com.kizias.readstory.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kizias.readstory.fragment_page_chapter;

public class PageChapterAdapter extends FragmentPagerAdapter {

    int chapter_count;

    public PageChapterAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        chapter_count = count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        fragment_page_chapter fragmentPageChapter = new fragment_page_chapter();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putInt("number", position);
        fragmentPageChapter.setArguments(bundle);
        return fragmentPageChapter;
    }

    @Override
    public int getCount() {
        return chapter_count;
    }
}
