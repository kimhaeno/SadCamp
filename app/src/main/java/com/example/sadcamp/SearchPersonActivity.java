package com.example.sadcamp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchPersonActivity extends AppCompatActivity {
    private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);

        ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(this);
        nameList = dbHelper.getAllNames();

        ListView listViewNames = findViewById(R.id.list_view_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        listViewNames.setAdapter(adapter);
        }
    }
