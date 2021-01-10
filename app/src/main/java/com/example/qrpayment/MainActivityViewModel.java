package com.example.qrpayment;

import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.*;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.regex.Pattern;

public class MainActivityViewModel {

    public static final MediaType JSON
            = MediaType.parse("application/json;charset=utf-8");


    public int validateInput(String email, String password) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email.isEmpty()) { //email empty
            return 0;
        } else if (pat.matcher(email).matches()) { //email ok
            return 1;
        }
        return 2; //email have wrong characters
    }

    String getUserByName(String email, String password) throws JSONException, IOException {
        //String url = "http://10.0.2.2:8080/Login";
        //String url = "http://localhost:8080/Login";
        String url = "http://192.168.1.100:8080/Login";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("<<Error", "Json exception!");
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response response = client.newCall(request).execute();
        Log.d("<<Error", "post request initiated");
        return response.body().string();
    }
}
