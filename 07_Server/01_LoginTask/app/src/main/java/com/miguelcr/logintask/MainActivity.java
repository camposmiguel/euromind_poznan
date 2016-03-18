package com.miguelcr.logintask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nick, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nick = (EditText)findViewById(R.id.editTextNick);
        pass = (EditText)findViewById(R.id.editTextPass);
    }

    public void doLogin(View view) {
        String nickUser = nick.getText().toString();
        String passUser = pass.getText().toString();

        // Do the request to the server
        new LoginTask().execute(nickUser,passUser);
    }

    private class LoginTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String nickname = params[0];
            String password = params[1];
            String urlRequestLogin = "http://rest.miguelcr.com/friender/login?nickname="+nickname+"&password="+password;

            String result = Util.getResultadoUrl(urlRequestLogin);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result==null) {
                Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
