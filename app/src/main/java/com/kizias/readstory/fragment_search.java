package com.kizias.readstory;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class fragment_search extends Fragment {

    Fragment selectedFragment;
    View view;
    SearchView search_view;
    TextView text_search_default;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        search_view = (SearchView) view.findViewById(R.id.search_view);
        text_search_default = (TextView) view.findViewById(R.id.text_search_default);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                text_search_default.setVisibility(View.GONE);
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