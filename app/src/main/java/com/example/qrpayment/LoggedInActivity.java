package com.example.qrpayment;

import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    LoggedInActivityViewModel loggedInActivityViewModel;
    User object;
    public Button btn_QR_CODE_SCAN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loggedInActivityViewModel = new LoggedInActivityViewModel();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        TextView left_corner_msg=findViewById(R.id.textView_left_corner_msg);
        btn_QR_CODE_SCAN=findViewById(R.id.btn_QR_Scan);
        String username = getIntent().getStringExtra("name");
        try {
            object=  (User) loggedInActivityViewModel.JsonString_to_Object(username);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        left_corner_msg.setText("welcome " +object.getFirstName()+" "+ object.getLastName());
        btn_QR_CODE_SCAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CameraViewActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_camera_view);
            }
        });
    }



}