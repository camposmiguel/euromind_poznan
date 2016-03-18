package com.miguelcr.listview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    String[] students;
    ArrayAdapter<String> studentsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. The listView component from the layout
        lista = (ListView) findViewById(R.id.listViewStudents);

        // 2. List of elements, students
        students = new String[]{"Grzegorz","Szymon","Artur","Macieg"};

        // 3. The adapter to connect ListView and String[]
        studentsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,students);

        // 4. Connect Adaptaer and ListView
        lista.setAdapter(studentsAdapter);

        // Set click item event
        lista.setOnItemClickListener(this);

        new GetUsersTask().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Item clicked: "+students[position],Toast.LENGTH_LONG).show();
        view.animate().rotationX(360).setDuration(2000).start();
    }

    private class GetUsersTask extends AsyncTask<Void,Void,JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... params) {
            String urlRequestUsers = "http://rest.miguelcr.com/killduck/ranking";

            JSONArray response = Util.getArrayResultado(urlRequestUsers);

            return response;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            try {

            students = new String[jsonArray.length()];

            for(int i=0; i<jsonArray.length(); i++) {

                JSONObject currentUser = jsonArray.getJSONObject(i);
                students[i] = currentUser.getString("nickname");
            }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            studentsAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,students);

            // 4. Connect Adaptaer and ListView
            lista.setAdapter(studentsAdapter);        }
    }
}
