package com.example.sadcamp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private int imagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        MyApp myApp = (MyApp) getApplicationContext();
        myDb = myApp.getDatabaseHelper();

        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("position");
        ArrayList<String> info = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.format("PHOTO #%d", pos));

        ImageView imageView = findViewById(R.id.photo_bigger);
        TextView workView = findViewById(R.id.workout);
        TextView dateView = findViewById(R.id.date);
        TextView weightView = findViewById(R.id.weight);

        imageView.setImageBitmap(myDb.getBitmapImage(pos));
        info = myDb.getImageInfo(pos);

        workView.setText("운동 이름:     " + info.get(0));
        dateView.setText("그때 몸무게:  " + info.get(1) + " kg");
        weightView.setText("날짜:             " + info.get(2));


        Log.d("TAG", String.format("before clicked %d", pos));

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("TAG", String.format("clicked %d", pos));
                deleteData(pos);
            }

        });
    }

    private void deleteData(int pos) {
        Log.d("DeleteData", "Deleting data with ID: " + pos);
        myDb.deleteData(pos);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
