package com.example.qrpayment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;

import androidx.appcompat.app.AppCompatActivity;

import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class LoggedInActivity extends AppCompatActivity {

    User object;
    public Button btn_QR_CODE_SCAN;
    public Button btn_history;
    public Button btn_logout;
    TextView textView_left_corner_msg;
    TextView textViewTime;
    Calendar calendar;
    String TimeString;
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
        calendar= Calendar.getInstance();
        TimeString=DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewTime=findViewById(R.id.textViewTime);
        textView_left_corner_msg = findViewById(R.id.textView_left_corner_msg);
        btn_QR_CODE_SCAN = findViewById(R.id.btn_QR_Scan);
        btn_history = findViewById(R.id.btn_history);
        btn_logout = findViewById(R.id.btn_logout);
        String username = getIntent().getStringExtra("name");
        try {
            object = (User) JsonIO.JsonString_to_Object(username, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        textViewTime.setText(TimeString);
        textView_left_corner_msg.setText("Welcome " + object.getFirstName() + " " + object.getLastName());
    }

    @Override
    protected void onResume() {
        Log.d("Lifecycle: ", "LoggedInActivity onResume");
        super.onResume();
        btn_QR_CODE_SCAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraViewActivity.class);
                startActivity(intent);
                setContentView(R.layout.activity_camera_view);
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                try {
                    intent.putExtra("userobject", JsonIO.Object_to_JsonString(object));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                setContentView(R.layout.activity_history);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getIntent().removeExtra("name");
               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
               setContentView(R.layout.activity_main);
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);

    }
}