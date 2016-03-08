package com.miguelcr.calculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView)findViewById(R.id.textViewScreen);

        Typeface type = Typeface.createFromAsset(getAssets(),"digital.ttf");
        screen.setTypeface(type);
    }

    public void buttonNumberClicked(View buttonClicked) {
        int id = buttonClicked.getId();

        String previousScreenText = screen.getText().toString();

        if(previousScreenText.equals("0")) {
            previousScreenText="";
        }

        switch (id) {
            case R.id.button9: // code to do when the user click
                                // on button '9'

                screen.setText(previousScreenText+"9");
                break;

            case R.id.button8:
                screen.setText(previousScreenText+"8");
                break;
            case R.id.button7:
                screen.setText(previousScreenText+"7");
                break;
            case R.id.button6:
                screen.setText(previousScreenText+"6");
                break;
            case R.id.button5:
                screen.setText(previousScreenText+"5");
                break;
            case R.id.button4:
                screen.setText(previousScreenText+"4");
                break;
            case R.id.button3:
                screen.setText(previousScreenText+"3");
                break;
            case R.id.button2:
                screen.setText(previousScreenText+"2");
                break;
            case R.id.button1:
                screen.setText(previousScreenText+"1");
                break;
            case R.id.button0:
                screen.setText(previousScreenText+"0");
                break;

        }
    }

    public void buttonDeleteClicked(View v) {
        String textOfScreen = screen.getText().toString();
        String newText = textOfScreen.substring(0, textOfScreen.length() - 1);

        if(newText.equals("")) {
            screen.setText("0");
        } else {
            screen.setText(newText);
        }
}

    public void buttonOperatorClicked (View v) {

    }
}
