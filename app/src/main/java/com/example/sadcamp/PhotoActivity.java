package com.example.sadcamp;

import android.content.Intent;
import android.app.Activity;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.sadcamp.DatabaseHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("position");

        DatabaseHelper myDb = new DatabaseHelper(this.getApplicationContext());


        Toolbar toolbar = findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.format("PHOTO #%d", pos));

        ImageView imageView = findViewById(R.id.photo_bigger);
        imageView.setImageBitmap(myDb.getBitmapImage(pos));


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
