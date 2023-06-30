package com.example.sadcamp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadcamp.CallAdapter;
import com.example.sadcamp.ContactData;
import com.example.sadcamp.R;

import java.util.ArrayList;

public class CallFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_call, container, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        ArrayList<ContactData> data = new ArrayList<ContactData>();
        data.add(new ContactData("John wick","john.wick@email.com",R.drawable.a));
        data.add(new ContactData("Robert j","robert.j@email.com",R.drawable.b));
        data.add(new ContactData("James Gunn","james.gunn@email.com",R.drawable.c));

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(new CallAdapter(data));

        return rootView;
    }
}