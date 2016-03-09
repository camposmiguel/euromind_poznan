package com.miguelcr.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    String[] students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. The listView component from the layout
        lista = (ListView) findViewById(R.id.listViewStudents);

        // 2. List of elements, students
        students = new String[]{"Grzegorz","Szymon","Artur","Macieg"};

        // 3. The adapter to connect ListView and String[]
        ArrayAdapter<String> studentsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,students);

        // 4. Connect Adaptaer and ListView
        lista.setAdapter(studentsAdapter);

        // Set click item event
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Item clicked: "+students[position],Toast.LENGTH_LONG).show();
        view.animate().rotationX(360).setDuration(2000).start();
    }
}
