package com.example.sadcamp;

import android.app.PendingIntent;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_SEND_SMS=2323;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("position");
        ContactData contactdata = (ContactData) intent.getSerializableExtra("data");


        Toolbar toolbar = findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.format("Profile #%d", pos));

        CircleImageView profileView = findViewById(R.id.profile_image);
        TextView nameView = findViewById(R.id.profile_name);
        TextView numberView = findViewById(R.id.profile_number);
        EditText editMsg = findViewById(R.id.editMsg);

        profileView.setImageResource(contactdata.getPic());
        nameView.setText(contactdata.getName());
        numberView.setText(contactdata.getPhoneNumber());

        Button callButton = findViewById(R.id.msg_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String strMsg = editMsg.getText().toString().replaceAll("[^0-9]", "");
                String strMsg = editMsg.getText().toString();
                String strPhoneNumber = contactdata.getPhoneNumber().replaceAll("[^0-9]", "");
                if(strPhoneNumber.equals("") || strPhoneNumber.length() < 10){
                    Toast.makeText(getApplicationContext(),"전화번호를 확인하세요",Toast.LENGTH_LONG).show();
                    return;

                }
                sendMsg(strPhoneNumber, "[From OWN WAN]\n" + strMsg);
            }
        });


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

    private void sendMsg(String phoneNum, String msg){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {//권한이 없다면

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSIONS_REQUEST_SEND_SMS);
        }else { //권한이 있다면 SMS를 보낸다.

            PendingIntent sendIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), PendingIntent.FLAG_IMMUTABLE);
            PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), PendingIntent.FLAG_IMMUTABLE);


            SmsManager smsManager = SmsManager.getDefault();
            try {
                smsManager.sendTextMessage(phoneNum, null, msg, sendIntent, deliveredIntent);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
