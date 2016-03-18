package com.miguelcr.navigationdrawer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {
    ImageView logo;
    EditText nickEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = (ImageView) findViewById(R.id.duckLogo);
        nickEditText = (EditText)findViewById(R.id.editTextNick);

        Picasso.with(this)
                .load("http://rest.miguelcr.com/assets/images/killduck.png")
                .into(logo);
    }

    public void doLogin(View view) {
        String nick = nickEditText.getText().toString();

        new LoginTask().execute(nick);
    }

    private class LoginTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String nickname = params[0];

            String urlRequestLogin = "http://rest.miguelcr.com/killduck/register?nickname="+nickname;

            String result = Util.getResultadoUrl(urlRequestLogin);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result==null) {
                Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        }
    }
}
