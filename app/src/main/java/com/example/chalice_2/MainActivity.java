package com.example.chalice_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phoneno,message;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneno=findViewById(R.id.phoneno);
        message=findViewById(R.id.message);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendsms();
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},100);
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendsms();
        }else{
            Toast.makeText(this, "Pemission failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendsms() {
        String phone_no=phoneno.getText().toString();
        String message_tosend=message.getText().toString();
        if(!phone_no.isEmpty() && !message_tosend.isEmpty()){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone_no,null,message_tosend,null,null);
            Toast.makeText(this, "Sent message successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sms not sent", Toast.LENGTH_SHORT).show();
        }
    }


}
