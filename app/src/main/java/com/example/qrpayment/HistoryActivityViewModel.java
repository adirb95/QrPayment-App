package com.example.qrpayment;

import android.os.StrictMode;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class HistoryActivityViewModel {
    private static final MediaType JSON =  MediaType.parse("application/json;charset=utf-8");
    String url;
    public JSONArray getPaymentsList(String userJsonObject) throws IOException, JSONException {
        url = "https://qr-payment.azurewebsites.net/history";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, userJsonObject);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response response = client.newCall(request).execute();
        String jsonList = response.body().string();
        return new JSONArray(jsonList);
    }
}
