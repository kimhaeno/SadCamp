package com.example.sadcamp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sadcamp.CallAdapter;
import com.example.sadcamp.ContactData;
import com.example.sadcamp.NewContactActivity;
import com.example.sadcamp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CallFragment extends Fragment {

    private static final String TAG = "CallFragment";

    private RecyclerView recyclerView;
    private CallAdapter adapter;
    ArrayList<ContactData> data = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call, container, false);

        FloatingActionButton fabAddContact = rootView.findViewById(R.id.fab_add_contact);
        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new NewContactActivity());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        data.add(new ContactData("John wick","john.wick@email.com",R.drawable.a));
        data.add(new ContactData("Robert j","robert.j@email.com",R.drawable.b));
        data.add(new ContactData("James Gunn","james.gunn@email.com",R.drawable.c));
        data.add(new ContactData("Ricky tales","rickey.tales@email.com",R.drawable.d));
        data.add(new ContactData("Micky mose","mickey.mouse@email.com",R.drawable.e));
        data.add(new ContactData("Pick War","pick.war@email.com",R.drawable.f));
        data.add(new ContactData("Leg piece","leg.piece@email.com",R.drawable.g));
        data.add(new ContactData("Apple Mac","apple.mac@email.com",R.drawable.h));
        data.add(new ContactData("John wick","john.wick@email.com",R.drawable.i));
        data.add(new ContactData("Robert j","robert.j@email.com",R.drawable.j));
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