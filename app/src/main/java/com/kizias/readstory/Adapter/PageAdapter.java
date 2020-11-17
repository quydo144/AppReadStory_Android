package com.kizias.readstory.Adapter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kizias.readstory.fragment_full;
import com.kizias.readstory.fragment_like;
import com.kizias.readstory.fragment_newupdate;
import com.kizias.readstory.fragment_stories;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragment_stories();
            case 1:
                return new fragment_newupdate();
            case 2:
                return new fragment_full();
            case 3:
                return new fragment_like();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
