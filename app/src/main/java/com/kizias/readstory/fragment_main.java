package com.kizias.readstory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.kizias.readstory.Adapter.PageAdapter;

public class fragment_main extends Fragment {

    View view;
    TabLayout tabLayout;
    TabItem moinhat, newcapnhat, full, danhgia, like, xemnhieu;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        Init();
        PageAdapter pageAdapter = new PageAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    protected void Init() {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        moinhat = (TabItem) view.findViewById(R.id.moinhat);
        newcapnhat = (TabItem) view.findViewById(R.id.newcapnhat);
        full = (TabItem) view.findViewById(R.id.full);
        like = (TabItem) view.findViewById(R.id.like);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }
}