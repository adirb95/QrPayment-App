package com.example.qrpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.ViewGroup;
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

public class NewHistory extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableLayout = new TableLayout(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(lp);
        try {
            JSONArray paymentsArray = getPaymentsList();
            createTable(paymentsArray);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        setContentView(tableLayout);
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
        TableRow header = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("No.");
        header.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Amount");
        header.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("Currency");
        header.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("Date");
        header.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText("Status");
        header.addView(tv4);
        tableLayout.addView(header);
        for (int i = 0; i < payments.length(); i++) {
            JSONObject jsonObject = payments.getJSONObject(i);
            TableRow row = new TableRow(this);
            TextView number = new TextView(this);
            number.setText(" " + i + 1);
            row.addView(number);
            TextView currency = new TextView(this);
            currency.setText(jsonObject.getString("currency"));
            row.addView(currency);
            TextView customer = new TextView(this);
            customer.setText(jsonObject.getString("customer"));
            row.addView(customer);
            TextView date = new TextView(this);
            date.setText(jsonObject.getString("date"));
            row.addView(date);
            TextView status = new TextView(this);
            status.setText(jsonObject.getString("status"));
            row.addView(status);
            tableLayout.addView(row);
        }
    }

}