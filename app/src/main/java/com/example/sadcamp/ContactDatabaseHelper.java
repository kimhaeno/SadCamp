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

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "person.db";
    public static final String TABLE_NAME = "person_data";
    public static final String COL2 = "name";
    public static final String COL3 = "photo"; // Blob type for Bitmap image

    public static final String COL4 = "bio";

    public static final String COL5 = "phoneNumber";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " + COL3 +" BLOB, " + COL4 + " TEXT, " + COL5 + " TEXT) ";
        Log.d("aaaaaaaaaaaaaaaaaaaaaaaaa", createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addUser(String name, byte[] image, String bio, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, image);
        contentValues.put(COL4, bio);
        contentValues.put(COL5, phoneNumber);

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
        Cursor res = db.rawQuery("SELECT " + COL3 + " FROM " + TABLE_NAME, null);
        if (res.moveToFirst()){
            do{
                byte[] imgByte = res.getBlob(0);
                images.add(BitmapFactory.decodeByteArray(imgByte,0, imgByte.length));
            }while (res.moveToNext());
        }
        res.close();
        return images;
    }

    public Bitmap getProfileImage(int position){
        Bitmap image = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL3 + " FROM " + TABLE_NAME + " WHERE ID = " + (position + 1), null);
        if (res.moveToFirst()){
            byte[] imgByte = res.getBlob(0);
            image = BitmapFactory.decodeByteArray(imgByte,0, imgByte.length);
        }

        res.close();
        return image;
    }
    public String[] getUserInfo(int position){
        String[] info = new String[3];

        info[0] = null;
        info[1] = null;
        info[2] = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL2 + ", " + COL4+
                " , " + COL5 + " FROM " + TABLE_NAME + " WHERE ID = " + (position + 1), null);
        if (res.moveToFirst()){
            info[0] = res.getString(0);
            info[1] = res.getString(1);
            info[2] = res.getString(2);
        }

        res.close();
        return info;
    }

    public ArrayList<ContactData> getAllProfiles(){
        int cnt = 0;
        ArrayList<ContactData> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT " + COL2 + " , " + COL3 + " , " + COL4 + " , " + COL5 + " FROM " + TABLE_NAME, null);
        if (res.moveToFirst()){
            do{
                String name = res.getString(0);
                Log.d("CONTACT!", name);
                byte[] imgByte = res.getBlob(1);
                Bitmap profile = BitmapFactory.decodeByteArray(imgByte,0, imgByte.length);

                String bio = res.getString(2);
                String phoneNumber = res.getString(3);
                data.add(new ContactData(name, phoneNumber, profile, bio) );
                cnt++;
            }while (res.moveToNext());
        }
        Log.d("CONTACTDATABASEHELPER", String.format("%d", cnt));
        res.close();
        return data;
    }

}

