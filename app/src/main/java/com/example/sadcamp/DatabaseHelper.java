package com.example.sadcamp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "person.db";
    public static final String TABLE_NAME = "person_data";
    public static final String COL2 = "name";
    public static final String COL3 = "age";
    public static final String COL4 = "birthday";
    public static final String COL5 = "photo"; // Blob type for Bitmap image

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 +" INTEGER, " + COL4 + " TEXT, " + COL5 + " BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addUser(String name, String age, String birthday, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, age);
        contentValues.put(COL4, birthday);
        contentValues.put(COL5, image);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // 데이터 삽입이 성공적으로 이루어지면 result에는 새로운 행의 ID가 저장됩니다.
        // 실패하면 -1이 저장됩니다.
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public ArrayList<Bitmap> getAllImages(){
        ArrayList<Bitmap>images = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL5 + " FROM " + TABLE_NAME, null);
        if (res.moveToFirst()){
            do{
                byte[] imgByte = res.getBlob(0);
                images.add(BitmapFactory.decodeByteArray(imgByte,0, imgByte.length));
            }while (res.moveToNext());
        }
        res.close();
        return images;
    }

    public Bitmap getBitmapImage(int position){
        Bitmap image = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL5 + " FROM " + TABLE_NAME + " WHERE ID = " + (position + 1), null);
        if (res.moveToFirst()){
            byte[] imgByte = res.getBlob(0);
            image = BitmapFactory.decodeByteArray(imgByte,0, imgByte.length);
        }

        res.close();
        return image;
    }
    //아니 ㅋㅋ...생일이랑 나이 실화냐?
    /*
    public String[] getPhotoInfo(int position){
        Bitmap image = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * " + " FROM " + TABLE_NAME + " WHERE ID = " + (position + 1), null);
        if (res.moveToFirst()){
            byte[] imgByte = res.getBlob(0);
            image = BitmapFactory.decodeByteArray(imgByte,0, imgByte.length);
        }

        res.close();
        return image;
    }*/

}

