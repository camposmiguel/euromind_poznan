package com.miguelcr.listviewcustom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listViewStudents);

        students = new ArrayList<>();

        students.add(new Student("Mary",18,'f'));
        students.add(new Student("Paul",19,'m'));
        students.add(new Student("Grzegorz",19,'m'));
        students.add(new Student("Artur",20,'m'));


        StudentAdapter studentAdapter = new StudentAdapter(this,R.layout.student_item_list,students);

        lista.setAdapter(studentAdapter);
    }
}
