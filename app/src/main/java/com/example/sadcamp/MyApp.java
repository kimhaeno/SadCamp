package com.example.sadcamp;

import android.app.Application;

public class MyApp extends Application {
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        // DatabaseHelper 객체를 생성하고 초기화합니다.
        databaseHelper = new DatabaseHelper(this);
    }

    // DatabaseHelper 객체에 대한 접근자 메서드를 추가합니다.
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}