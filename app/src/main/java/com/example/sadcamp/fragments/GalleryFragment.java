package com.example.sadcamp.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sadcamp.DatabaseHelper;
import com.example.sadcamp.GalleryAdapter;
import com.example.sadcamp.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GridView gridView;
    private DatabaseHelper myDb;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GalleryAdapter galleryAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        myDb = new DatabaseHelper(getActivity());

        ArrayList<Bitmap> imageList = myDb.getAllImages();

        galleryAdapter = new GalleryAdapter(getActivity(), imageList);

        gridView = rootView.findViewById(R.id.gridView);
        gridView.setAdapter(galleryAdapter);

        swipeRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getContacts();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    private void getContacts() {
        ArrayList<Bitmap> updatedContacts = myDb.getAllImages();
        galleryAdapter = new GalleryAdapter(getActivity(), updatedContacts);
        gridView.setAdapter(galleryAdapter);
    }


}



