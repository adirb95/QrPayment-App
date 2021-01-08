package com.example.qrpayment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();
    private String input_email, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_button = findViewById(R.id.login_btn);
        EditText editText_email_field = findViewById(R.id.email_field);
        EditText editText_password_field = findViewById(R.id.password_field);
        TextView editText_warnings_field = findViewById(R.id.textView_warnings);
        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_email = editText_email_field.getText().toString();
                input_password = editText_password_field.getText().toString();
                /*mainActivityViewModel.validateInput(input_email,input_password)*/
                /*String user = */
                if (input_email.isEmpty()) {
                    Log.d("<<Error", "empty email");
                }
                switch (mainActivityViewModel.validateInput(input_email, input_password)) {
                    case 0: {                                                                           //wrong email or password
                        editText_warnings_field.setText("Wrong email / password. Please try again.");
                        break;
                    }
                    case 1: {
                        try {
                           String userobject =mainActivityViewModel.getUserByName(input_email, input_password);    //success
                            if(!userobject.isEmpty()) {
                              Intent i = new Intent(getApplicationContext(), LoggedInActivity.class);
                              i.putExtra("name", userobject);
                              startActivity(i);
                              setContentView(R.layout.activity_logged_in);

                          }
                            editText_warnings_field.setText("Email or password not valid!");
                            break;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    default: {
                        editText_warnings_field.setText("Please enter a valid email");                          //email have wrong parameter's/
                        break;
                    }
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

