package com.example.qrpayment;

import android.annotation.SuppressLint;
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
    public Button btn_history;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loggedInActivityViewModel = new LoggedInActivityViewModel();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        TextView left_corner_msg=findViewById(R.id.textView_left_corner_msg);
        btn_QR_CODE_SCAN=findViewById(R.id.btn_QR_Scan);
        btn_history = findViewById(R.id.btn_history);
        String username = getIntent().getStringExtra("name");
        try {
            object=  (User) JsonIO.JsonString_to_Object(username,User.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        left_corner_msg.setText("Welcome " +object.getFirstName()+" "+ object.getLastName());
        btn_QR_CODE_SCAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CameraViewActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_camera_view);
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                try {
                    intent.putExtra("userobject",JsonIO.Object_to_JsonString(object));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                setContentView(R.layout.activity_history);
            }
        });
    }



}