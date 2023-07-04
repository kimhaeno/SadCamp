package com.example.sadcamp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class NewContactActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;

    private EditText name;
    private EditText phoneNumber;
    private ImageButton imageButton;

    private EditText bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        name = findViewById(R.id.editTextTextPersonName);
        phoneNumber = findViewById(R.id.editTextTextPersonName2);
        imageButton = findViewById(R.id.imageButton);
        bio = findViewById(R.id.edit_bio);

        Button saveButton = findViewById(R.id.button2);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String phoneNumberStr = phoneNumber.getText().toString();
                String bioStr = bio.getText().toString();

                // 이미지 버튼에 설정된 이미지를 Bitmap 형태로 가져오기
                BitmapDrawable drawable = (BitmapDrawable) imageButton.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, 200,200, false);

                // Bitmap을 byte array로 변환
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] imageByteArray = byteArrayOutputStream.toByteArray();

                if(nameStr==null || imageByteArray==null ||phoneNumberStr==null)
                    return;

                // DatabaseHelper 인스턴스 생성, addUser 메소드 호출하여 사용자 정보 저장
                ContactDatabaseHelper db = new ContactDatabaseHelper(NewContactActivity.this);
                db.addUser(nameStr, imageByteArray, bioStr, phoneNumberStr);

                Toast.makeText(NewContactActivity.this, "사용자 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();

                // 액티비티를 종료하고 이전 화면으로 돌아감
                finish();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 갤러리 앱을 실행하기 위한 인텐트 생성
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");

                // 갤러리 앱을 실행하여 이미지 선택할 수 있는 액티비티 호출
                startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE_GALLERY);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageButton.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

