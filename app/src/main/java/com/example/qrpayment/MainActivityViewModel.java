package com.example.qrpayment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MainActivityViewModel {
    Context mContext;
    public static final MediaType JSON
            = MediaType.parse("application/json;charset=utf-8");

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public int validateInput(String email, String password) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email.isEmpty() || password.isEmpty()) { //email empty or password
            return 0;
        } else if (pat.matcher(email).matches()) { //email ok
            return 1;
        }
        return 2; //email have wrong characters
    }

    String getUserByName(String email, String password) throws JSONException, IOException {
        //String url = "http://10.0.2.2:8080/Login";
        //String url = "http://localhost:8080/Login";
        /* String url = "http://192.168.1.100:8080/Login";*/
        String url = "https://qr-payment.azurewebsites.net/Login";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("<<Error", "Json exception!");
        }
        OkHttpClient client = new OkHttpClient();
        client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
        /*OkHttpClient client = new OkHttpClient();*/
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response response = client.newCall(request).execute();
        Log.d("<<Error", "post request initiated");
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return "No response from Server";
    }

    public boolean isInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

    }


}
