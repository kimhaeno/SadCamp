package com.example.sadcamp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadcamp.GalleryAdapter;
import com.example.sadcamp.R;
import com.example.sadcamp.photoData;

import android.widget.GridView;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    GridView gridView;

    public int[] photoArray = {R.drawable.a,
            R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.g};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), photoArray);
        gridView = rootView.findViewById(R.id.gridView);
        gridView.setAdapter(galleryAdapter);



        return rootView;
    }
}

