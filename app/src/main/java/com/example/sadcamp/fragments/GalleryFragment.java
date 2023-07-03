package com.example.sadcamp.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.sadcamp.DatabaseHelper;
import com.example.sadcamp.GalleryAdapter;
import com.example.sadcamp.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    GridView gridView;
    DatabaseHelper myDb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        myDb = new DatabaseHelper(getActivity());

        ArrayList<Bitmap> imageList = myDb.getAllImages();

        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), imageList);

        gridView = rootView.findViewById(R.id.gridView);
        gridView.setAdapter(galleryAdapter);

        return rootView;
    }
}


