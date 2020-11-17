package com.kizias.readstory.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kizias.readstory.fragment_page_chapter;

public class PageChapterAdapter extends FragmentPagerAdapter {


    public PageChapterAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new fragment_page_chapter();
    }

    @Override
    public int getCount() {
        return 0;
    }
}
