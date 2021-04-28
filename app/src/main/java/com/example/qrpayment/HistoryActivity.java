
package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class HistoryActivity extends AppCompatActivity {


    TableLayout tableLayout;
    HistoryActivityViewModel historyActivityViewModel = new HistoryActivityViewModel();
    String userJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Lifecycle: ", "NewHistory onCreate");
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void createTable(JSONArray payments) throws JSONException {
        TableRow header = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" No. ");
        tv0.setGravity(Gravity.CENTER);
        tv0.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv0.setTextColor(Color.BLUE);
        header.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Amount ");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv1.setTextColor(Color.BLUE);
        header.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Currency ");
        tv2.setGravity(Gravity.CENTER);
        tv2.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv2.setTextColor(Color.BLUE);
        header.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Company ");
        tv3.setGravity(Gravity.CENTER);
        tv3.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv3.setTextColor(Color.BLUE);
        header.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" Date ");
        tv4.setGravity(Gravity.CENTER);
        tv4.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv4.setTextColor(Color.BLUE);
        header.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText("    Status     ");
        tv5.setGravity(Gravity.CENTER);
        tv5.setTypeface(tv0.getTypeface(), Typeface.BOLD);
        tv5.setTextColor(Color.BLUE);
        header.addView(tv5);
        tableLayout.addView(header);
        for (int i = 1; i < (payments.length() + 1); i++) {
            JSONObject jsonObject = payments.getJSONObject(i - 1);
            TableRow row = new TableRow(this);
            TextView number = new TextView(this);
            number.setText("" + i);
            number.setGravity(Gravity.CENTER);
            number.setTextColor(Color.BLACK);
            row.addView(number);
            TextView amount = new TextView(this);
            amount.setText(jsonObject.getString("amount"));
            amount.setGravity(Gravity.CENTER);
            amount.setTextColor(Color.BLACK);
            row.addView(amount);
            TextView currency = new TextView(this);
            currency.setText(jsonObject.getString("currency"));
            currency.setGravity(Gravity.CENTER);
            currency.setTextColor(Color.BLACK);
            row.addView(currency);
            TextView companyName = new TextView(this);
            companyName.setText(jsonObject.getString("companyName"));
            companyName.setGravity(Gravity.CENTER);
            companyName.setTextColor(Color.BLACK);
            row.addView(companyName);
            TextView date = new TextView(this);
            date.setText(jsonObject.getString("date"));
            date.setGravity(Gravity.CENTER);
            date.setTextColor(Color.BLACK);
            row.addView(date);
            TextView status = new TextView(this);
            status.setText(jsonObject.getString("status"));
            status.setGravity(Gravity.CENTER);
            status.setTextColor(Color.BLACK);
            row.addView(status);
            tableLayout.addView(row);
        }
    }

    @Override
    protected void onStart() {
        Log.d("Lifecycle: ", "HistoryActivity onStart");
        super.onStart();
        tableLayout = new TableLayout(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(lp);
        userJsonString = getIntent().getStringExtra("userobject");
        try {
            JSONArray paymentsArray = historyActivityViewModel.getPaymentsList(userJsonString);
            createTable(paymentsArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        setContentView(tableLayout);
    }

    @Override
    protected void onResume() {
        Log.d("Lifecycle: ", "HistoryActivity onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Lifecycle: ", "HistoryActivity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Lifecycle: ", "HistoryActivity onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Lifecycle: ", "HistoryActivity onRestart");
        setContentView(R.layout.activity_history);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle: ", "HistoryActivity onDestroy");
        super.onDestroy();
    }
}