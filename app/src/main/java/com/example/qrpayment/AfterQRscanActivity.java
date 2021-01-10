package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;

public class AfterQRscanActivity extends AppCompatActivity {

    String JsonString;
    MerchantNewPayment newPayment;
    Button ApproveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_qr_scan);
        TextView textViewCompanysName = findViewById(R.id.textViewCompanysName);
        TextView textViewAmountToPay = findViewById(R.id.textViewAmountToPay);
        ApproveBtn = findViewById(R.id.btn_Approve);
        JsonString = getIntent().getStringExtra("QRDetails");
        if (!JsonString.isEmpty()) {
            try {
                newPayment = (MerchantNewPayment) JsonIO.JsonString_to_Object(JsonString, MerchantNewPayment.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            textViewCompanysName.setText(newPayment.getMerchantName());
            textViewAmountToPay.setText(new Double(newPayment.getTransactionAmount()).toString());
        }
    }
}