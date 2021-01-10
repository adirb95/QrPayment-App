package com.example.qrpayment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    User userObject;
    public Button btn_QR_CODE_SCAN;
    public Button btn_history;
    TextView left_corner_msg;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Lifecycle: ", "LoggedInActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }

    @Override
    protected void onStart() {
        Log.d("Lifecycle: ", "LoggedInActivity onStart");
        super.onStart();
        left_corner_msg=findViewById(R.id.textView_left_corner_msg);
        btn_QR_CODE_SCAN=findViewById(R.id.btn_QR_Scan);
        btn_history = findViewById(R.id.btn_history);
        String username = getIntent().getStringExtra("name");
        try {
            userObject=  (User) JsonIO.JsonString_to_Object(username,User.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        left_corner_msg.setText("Welcome " +userObject.getFirstName()+" "+ userObject.getLastName());
    }

    @Override
    protected void onResume() {
        Log.d("Lifecycle: ", "LoggedInActivity onResume");
        super.onResume();
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
                startActivity(intent);
                setContentView(R.layout.activity_history);
            }
        });
    }

    @Override
    protected void onPause() {
        Log.d("Lifecycle: ", "LoggedInActivity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Lifecycle: ", "LoggedInActivity onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Lifecycle: ", "LoggedInActivity onRestart");
        setContentView(R.layout.activity_logged_in);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle: ", "MainActivity onDestroy");
        super.onDestroy();
    }
}