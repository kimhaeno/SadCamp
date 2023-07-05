package com.example.sadcamp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "photo.db";
    public static final String TABLE_NAME = "photo_data";
    public static final String COL2 = "workout";
    public static final String COL3 = "weight";
    public static final String COL4 = "date";
    public static final String COL5 = "photo"; // Blob type for Bitmap image
    public int history = 0;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 +" INTEGER, " + COL4 + " TEXT, " + COL5 + " BLOB)";
        db.execSQL(createTable);
        Log.d("##Creating##", "Create");
        history = 0;
    }

    public void deleteData(int pos) {
        SQLiteDatabase db = this.getWritableDatabase();
        int imageId = 0;

        Cursor res = db.rawQuery("SELECT ID FROM " + TABLE_NAME + " WHERE ID = (SELECT MAX(ID) FROM "
                + TABLE_NAME + " LIMIT " + (pos + 1) + " )", null);

        if(res.moveToFirst()){
            imageId = res.getInt(0);
        }



        db.execSQL("DELETE FROM "+ TABLE_NAME + " WHERE ID = " + String.format("%d", imageId));
        Log.d("Helper!", "DELETE FROM "+ TABLE_NAME + " WHERE rowID = " + String.format("%d", imageId));

        res.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addUser(String workout, String weight, String date, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, workout);
        contentValues.put(COL3, weight);
        contentValues.put(COL4, date);
        contentValues.put(COL5, image);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // 데이터 삽입이 성공적으로 이루어지면 result에는 새로운 행의 ID가 저장됩니다.
        // 실패하면 -1이 저장됩니다.
        if(result == -1)
            return false;

        Log.d("##Addition##", "adding");

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

    public void insert_with_name(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);  // Assuming you have a "name" column

        db.insert("names", null, values);  // Assuming you have a "names" table
        db.close();
    }

    public Bitmap getBitmapImage(int position){
        Bitmap image = null;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT " + COL5 + " FROM " + TABLE_NAME + " WHERE ID = (SELECT MAX(ID) FROM "
                + TABLE_NAME + " ) LIMIT " + (position + 1) , null);
        if (res.moveToFirst()){
            byte[] imgByte = res.getBlob(0);
            image = BitmapFactory.decodeByteArray(imgByte,0, imgByte.length);
        }

        res.close();
        return image;
    }

    public ArrayList<String> getImageInfo(int position){
        ArrayList<String> info = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT " + COL2 +" , " + COL3 +" , " + COL4
                + " FROM " + TABLE_NAME + " WHERE ID = (SELECT MAX(ID) FROM "
                + TABLE_NAME + " ) LIMIT " + (position + 1) , null);
        if (res.moveToFirst()){
            info.add(res.getString(0));
            info.add(res.getString(1));
            info.add(res.getString(2));
        }

        res.close();
        return info;
    }


}

