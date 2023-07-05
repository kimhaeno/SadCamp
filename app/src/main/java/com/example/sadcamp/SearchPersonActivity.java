/*
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sadcamp.ContactDatabaseHelper;

import java.util.ArrayList;

public class SearchPersonActivity extends AppCompatActivity {
    private ArrayList<String> nameList;
    private RecyclerView recyclerView;
    private WithPersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);

        ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(this);
        nameList = dbHelper.getAllNames();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WithPersonAdapter(nameList);
        recyclerView.setAdapter(adapter);

        Button saveButton = findViewById(R.id.saveButton);  // Assuming you have a "save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> checkedNames = adapter.getCheckedNames();

                // Set the result to pass back to the FreeFragment
                Intent resultIntent = new Intent();
                resultIntent.putStringArrayListExtra("checkedNames", checkedNames);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();  // This will close the current activity
            }
        });
    }}
    */
