package com.example.sadcamp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadcamp.GallaryAdapter;
import com.example.sadcamp.R;
import android.widget.GridView;

public class GallaryFragment extends Fragment {

    GridView gridView;

    public int[] imageArray = {
            R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallary, container, false);

        gridView = rootView.findViewById(R.id.gridView);
        gridView.setAdapter(new GallaryAdapter(requireActivity(), imageArray));

        return rootView;
    }
}

