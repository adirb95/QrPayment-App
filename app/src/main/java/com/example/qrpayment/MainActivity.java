package com.example.qrpayment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    EditText editText_email_field, editText_password_field;
    TextView editText_warnings_field;
    private String input_email, input_password;
    ImageButton login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Lifecycle: ", "MainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mainActivityViewModel = new MainActivityViewModel();
        mainActivityViewModel.setContext(MainActivity.this);
    }

    @Override
    protected void onStart() {
        Log.d("Lifecycle: ", "MainActivity onStart");
        super.onStart();
        login_button = findViewById(R.id.imageButton);
        editText_email_field = findViewById(R.id.email_field);
        editText_password_field = findViewById(R.id.password_field);
        editText_warnings_field = findViewById(R.id.textView_warnings);
    }

    @Override
    protected void onResume() {
        Log.d("Lifecycle: ", "MainActivity onResume");
        super.onResume();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        if (mainActivityViewModel.isInternetConnection()) {
                            try {
                                String userobject = mainActivityViewModel.getUserByName(input_email, input_password);
                                if (!userobject.isEmpty() && !userobject.equals("No response from Server")){
                                    //success
                                    Intent i = new Intent(getApplicationContext(), LoggedInActivity.class);
                                    i.putExtra("name", userobject);
                                    startActivity(i);
                                    /* setContentView(R.layout.activity_logged_in);*/
                                    break;
                                }
                                if(!userobject.isEmpty()) editText_warnings_field.setText("Email or password not valid! ");
                                editText_warnings_field.setText(userobject);
                                break;
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                        editText_warnings_field.setText("No Ethernet connection please try agian");
                        break;
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
        Log.d("Lifecycle: ", "MainActivity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("Lifecycle: ", "MainActivity onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Lifecycle: ", "MainActivity onRestart");
        setContentView(R.layout.activity_main);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("Lifecycle: ", "MainActivity onDestroy");
        super.onDestroy();
    }
}

