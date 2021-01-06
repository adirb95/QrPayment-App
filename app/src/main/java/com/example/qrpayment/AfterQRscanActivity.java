package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;

public class AfterQRscanActivity extends AppCompatActivity {

    String JsonString;
    Payment newPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_qr_scan);
        TextView textViewCompanysName=findViewById(R.id.textView_CompanysName);
        TextView textViewAmountToPay=findViewById(R.id.textView_AmountToPay);
        JsonString= getIntent().getStringExtra("QRDetails");
       /* try {*/
            if(!JsonString.isEmpty()) {

               Toast.makeText(AfterQRscanActivity.this,JsonString,Toast.LENGTH_LONG).show();
                /* newPayment = (Payment) jsonIO.JsonString_to_Object(JsonString, Payment.class);*/
                /*textViewCompanysName.setText(newPayment.get);*/
                System.out.println(JsonString);
            }
                /*
           }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

    }
}