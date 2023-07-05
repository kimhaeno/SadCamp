package com.example.sadcamp.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.sadcamp.DatabaseHelper;
import com.example.sadcamp.R;
import com.example.sadcamp.SearchPersonActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FreeFragment extends Fragment {

    EditText name;
    EditText age;
    Button birthday;
    ImageButton imageButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Date curDate = new Date(); // 현재
    final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    // SimpleDateFormat 으로 포맷 결정
    String result = dataFormat.format(curDate);

    public FreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_free, container, false);
        // 프래그먼트에 findViewByld 적용위해 View 선언
        name = view.findViewById(R.id.editTextTextPersonName);
        age = view.findViewById(R.id.editTextTextPersonName2);

        birthday = view.findViewById(R.id.button);
        birthday.setText(result); // 오늘 날짜로 초기화
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(); // 생년월일 버튼 클릭 시 showDateDialog() 함수 호출
            }
        });

        Button plus = view.findViewById(R.id.button4);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SearchPersonActivity 실행
                Intent intent = new Intent(getActivity(), SearchPersonActivity.class);
                startActivity(intent);
            }
        });

        Button save = view.findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String ageStr = age.getText().toString();
                String birthStr = birthday.getText().toString();

                //이미지 버튼에 설정된 이미지를 Bitmap 형태로 가져오기
                BitmapDrawable drawable = (BitmapDrawable) imageButton.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                //Bitmap을 byte array로 변환
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[]byteArray = byteArrayOutputStream.toByteArray();

                //DatabaseHelper 인스턴스 생성, addUser 메소드 호출 사용자 정보 저장
                DatabaseHelper db = new DatabaseHelper(getContext());
                db.addUser(nameStr, ageStr, birthStr, byteArray);

                Toast.makeText(getContext(), "운동 기록 저장 완료"
                        ,Toast.LENGTH_SHORT).show();
            }
        }); // 저장 버튼 클릭 시 입력한 정보 표시


        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 카메라 권한이 있는지 확인합니다.
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // 카메라 권한이 없다면 권한을 요청합니다.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    // 이미 권한이 있다면 카메라 앱을 실행합니다.
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

        return view;
    }

    private static final int REQUEST_CAMERA_PERMISSION = 1001;

    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();
        try {
            curDate = dataFormat.parse(birthday.getText().toString());
            // 문자열로 된 생년월일을 Date로 파싱
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        calendar.setTime(curDate);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        // 년,월,일 넘겨줄 변수

        DatePickerDialog dialog = new DatePickerDialog(getContext(),  birthDateSetListener,  curYear, curMonth, curDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener birthDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, month);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, day);
            // 달력의 년월일을 버튼에서 넘겨받은 년월일로 설정

            Date curDate = selectedCalendar.getTime(); // 현재를 넘겨줌
            setSelectedDate(curDate);
        }
    };

    private void setSelectedDate(Date curDate) {
        String selectedDateStr = dataFormat.format(curDate);
        birthday.setText(selectedDateStr); // 버튼의 텍스트 수정
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                if(imageBitmap != null) {
                    //Bitmap rotatedBitmap = rotateImageIfRequired(imageBitmap, getContext(), data.getData()); - 이 부분 주석 처리
                    Bitmap croppedBitmap = centerCropBitmap(imageBitmap, imageButton.getHeight(), imageButton.getWidth());
                    imageButton.setImageBitmap(croppedBitmap);
                }
            }
        }
    }



    private Bitmap centerCropBitmap(Bitmap bitmap, int targetHeight, int targetWidth) {
        float bitmapRatio = (float) bitmap.getWidth() / bitmap.getHeight();
        float targetRatio = (float) targetWidth / targetHeight;

        int newWidth;
        int newHeight;
        int offsetX = 0;
        int offsetY = 0;

        // 이미지를 이미지 버튼의 가로 세로 비율에 맞게 조정
        if (bitmapRatio > targetRatio) {
            newWidth = (int) (bitmap.getHeight() * targetRatio);
            newHeight = bitmap.getHeight();
            offsetX = (bitmap.getWidth() - newWidth) / 2;
        } else {
            newWidth = bitmap.getWidth();
            newHeight = (int) (bitmap.getWidth() / targetRatio);
            offsetY = (bitmap.getHeight() - newHeight) / 2;
        }

        // 새로운 크기가 0 또는 음수인지 검증
        if (newWidth <= 0 || newHeight <= 0) {
            throw new IllegalArgumentException("Invalid dimensions.");
        }

        // 이미지 잘라내기
        bitmap = Bitmap.createBitmap(bitmap, offsetX, offsetY, newWidth, newHeight);

        // 이미지 크기 조정
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }
}