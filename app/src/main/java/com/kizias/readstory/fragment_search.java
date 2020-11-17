package com.kizias.readstory;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class fragment_search extends Fragment {

    Fragment selectedFragment;
    View view;
    SearchView search_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        search_view = (SearchView) view.findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                selectedFragment = new fragment_search_detail();
                Bundle bundle = new Bundle();
                bundle.putString("key_search", query);
                selectedFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_search_detail, selectedFragment).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }
}