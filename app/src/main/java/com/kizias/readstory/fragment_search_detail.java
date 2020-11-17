package com.kizias.readstory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class fragment_search_detail extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_detail, container, false);
        Toast.makeText(getContext(), getArguments().getString("key_search", ""), Toast.LENGTH_SHORT).show();
        return view;
    }
}