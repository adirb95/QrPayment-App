package com.example.qrpayment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    private String input_email, input_password;
    ImageButton login_button;
    EditText editText_email_field;
    EditText editText_password_field;
    TextView editText_warnings_field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         login_button = findViewById(R.id.imageButton);
        editText_email_field = findViewById(R.id.email_field);
         editText_password_field = findViewById(R.id.password_field);
         editText_warnings_field = findViewById(R.id.textView_warnings);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();
                input_email = editText_email_field.getText().toString();
                input_password = editText_password_field.getText().toString();
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

                        } catch (JSONException | IOException e) {
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

