package com.example.sadcamp.fragments;

import android.os.Bundle;



import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadcamp.CallAdapter;
import com.example.sadcamp.ContactData;
import com.example.sadcamp.R;

import java.util.ArrayList;

public class CallFragment extends Fragment {

    private static final String TAG = "CallFragment";

    private RecyclerView recyclerView;
    private CallAdapter adapter;
    ArrayList<ContactData> data = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreate");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_call,
                container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        data.add(new ContactData("John wick","john.wick@email.com",R.drawable.a));
        data.add(new ContactData("Robert j","robert.j@email.com",R.drawable.b));
        data.add(new ContactData("James Gunn","james.gunn@email.com",R.drawable.c));
        data.add(new ContactData("Ricky tales","rickey.tales@email.com",R.drawable.d));
        data.add(new ContactData("Micky mose","mickey.mouse@email.com",R.drawable.e));
        data.add(new ContactData("Pick War","pick.war@email.com",R.drawable.f));
        data.add(new ContactData("Leg piece","leg.piece@email.com",R.drawable.g));
        data.add(new ContactData("Apple Mac","apple.mac@email.com",R.drawable.g));
        data.add(new ContactData("John wick","john.wick@email.com",R.drawable.a));
        data.add(new ContactData("Robert j","robert.j@email.com",R.drawable.b));
        data.add(new ContactData("James Gunn","james.gunn@email.com",R.drawable.c));
        data.add(new ContactData("Ricky tales","rickey.tales@email.com",R.drawable.d));
        data.add(new ContactData("Micky mose","mickey.mouse@email.com",R.drawable.e));
        data.add(new ContactData("Pick War","pick.war@email.com",R.drawable.f));
        data.add(new ContactData("Leg piece","leg.piece@email.com",R.drawable.g));
        data.add(new ContactData("Apple Mac","apple.mac@email.com",R.drawable.g));

        adapter = new CallAdapter(getActivity(), data);

        Log.d(TAG, "array constructed");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}