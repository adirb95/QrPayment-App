
package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class HistoryActivity extends AppCompatActivity {


    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tableLayout = findViewById(R.id.history_table);
        try {
            JSONArray paymentsArray = getPaymentsList();
            createTable(paymentsArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    public JSONArray getPaymentsList() throws IOException, JSONException {
        String url = "http://192.168.1.223:8080/history";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response response = client.newCall(request).execute();
        String jsonList = response.body().string();
        JSONArray jsonarray = new JSONArray(jsonList);
        return jsonarray;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void createTable(JSONArray payments) throws JSONException {
        TableRow header = new TableRow(HistoryActivity.this);
        TextView tv0 = new TextView(HistoryActivity.this);
        tv0.setText("No.");
        tv0.setTextColor(android.R.color.black);
        header.addView(tv0);
        TextView tv1 = new TextView(HistoryActivity.this);
        tv1.setText("Amount");
        tv1.setTextColor(android.R.color.black);
        header.addView(tv1);
        TextView tv2 = new TextView(HistoryActivity.this);
        tv2.setText("Currency");
        tv2.setTextColor(android.R.color.black);
        header.addView(tv2);
        TextView tv3 = new TextView(HistoryActivity.this);
        tv3.setText("Date");
        tv3.setTextColor(android.R.color.black);
        header.addView(tv3);
        TextView tv4 = new TextView(HistoryActivity.this);
        tv4.setText("Status");
        tv4.setTextColor(android.R.color.black);
        header.addView(tv4);
        tableLayout.addView(header);
        for (int i = 0; i < payments.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = payments.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TableRow row = new TableRow(HistoryActivity.this);
            TextView number = new TextView(HistoryActivity.this);
            number.setText(" " + i + 1);
            number.setTextColor(android.R.color.black);
            number.setGravity(Gravity.CENTER);
            row.addView(number);
            TextView currency = new TextView(HistoryActivity.this);
            try {
                currency.setText(jsonObject.getString("currency"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            currency.setTextColor(android.R.color.black);
            currency.setGravity(Gravity.CENTER);
            row.addView(currency);
            TextView customer = new TextView(HistoryActivity.this);
            try {
                customer.setText(jsonObject.getString("customer"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            customer.setTextColor(android.R.color.black);
            customer.setGravity(Gravity.CENTER);
            row.addView(customer);
            TextView date = new TextView(HistoryActivity.this);
            try {
                date.setText(jsonObject.getString("date"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            date.setTextColor(android.R.color.black);
            date.setGravity(Gravity.CENTER);
            row.addView(date);
            TextView status = new TextView(HistoryActivity.this);
            try {
                status.setText(jsonObject.getString("status"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            status.setTextColor(android.R.color.black);
            status.setGravity(Gravity.CENTER);
            row.addView(status);
            tableLayout.addView(row);
        }
    }
}

