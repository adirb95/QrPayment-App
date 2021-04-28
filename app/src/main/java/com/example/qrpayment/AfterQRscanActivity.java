package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;

public class AfterQRscanActivity extends AppCompatActivity {

    String JsonString, username;
    MerchantNewPayment newPayment;
    Button ApproveBtn, CancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Lifecycle: ", "AfterQRscanActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_qr_scan);
    }

    @Override
    protected void onStart() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onStart");
        super.onStart();
        TextView textViewCompanyName = findViewById(R.id.textViewCompanyName);
        TextView textViewAmountToPay = findViewById(R.id.textViewAmountToPay);
        TextView textViewCurrency = findViewById(R.id.textViewCurrency);
        ApproveBtn = findViewById(R.id.btn_Approve);
        CancelBtn = findViewById(R.id.btn_Cancel);
        username = getIntent().getStringExtra("name");
        JsonString = getIntent().getStringExtra("QRDetails");
        if (!JsonString.isEmpty()) {
            try {
                newPayment = JsonIO.JsonString_to_Object(JsonString, MerchantNewPayment.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            textViewCompanyName.setText(newPayment.getMerchantName());
            textViewAmountToPay.setText(new Double(newPayment.getPrice()).toString());
            textViewCurrency.setText(newPayment.getTransactionCurrency());
        }
    }

    @Override
    protected void onResume() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onResume");
        super.onResume();
        ApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoggedInActivity.class);
                intent.putExtra("name", username);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onRestart");
        setContentView(R.layout.activity_after_qr_scan);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle: ", "AfterQRscanActivity onDestroy");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

}