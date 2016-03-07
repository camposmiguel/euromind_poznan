package com.miguelcr.helloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references
        username = (EditText)findViewById(R.id.editTextUsername);
        btnLogin = (Button)findViewById(R.id.buttonLogin);
        password = (EditText)findViewById(R.id.editTextPassword);
        layout = (LinearLayout)findViewById(R.id.layoutMain);


        // Set properties for the components
        username.setText("poznanstudents");
        layout.setBackgroundColor(Color.YELLOW);
    }

    public void changeBgColor(View button) {
        int idClickedButton = button.getId();

        switch(idClickedButton){
            case R.id.buttonRed: layout.setBackgroundColor(Color.RED);
                break;
            case R.id.buttonGreen: layout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.buttonYellow: layout.setBackgroundColor(Color.YELLOW);
                break;
        }

    }
}
