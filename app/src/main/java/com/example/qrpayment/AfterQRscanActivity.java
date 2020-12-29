package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;

public class AfterQRscanActivity extends AppCompatActivity {
    JsonIO jsonIO;
    String JsonString;
    Payment newPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        jsonIO=new JsonIO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_q_rscan);
        JsonString= getIntent().getStringExtra("QRDetails");
        try {
            newPayment=(Payment)jsonIO.JsonString_to_Object(JsonString,Payment.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}