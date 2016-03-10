package com.miguelcr.studentgreendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miguelcr.studentgreendao.database.DaoSession;
import com.miguelcr.studentgreendao.database.Student;
import com.miguelcr.studentgreendao.database.StudentDao;

public class NewStudentActivity extends AppCompatActivity implements View.OnClickListener {
    String sex = "m";
    EditText name, age;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        name = (EditText) findViewById(R.id.editTextName);
        age = (EditText) findViewById(R.id.editTextAge);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
    }

    public void onRadioButtonClicked(View v) {
        int idRadioButton = v.getId();

        if(idRadioButton==R.id.radio_male) {
            sex = "m";
        } else {
            sex = "f";
        }
    }

    @Override
    public void onClick(View v) {
        DaoSession dbSession = DatabaseConnection.getConnection(this);

        StudentDao studentDao = dbSession.getStudentDao();

        Student newStudent = new Student();
        newStudent.setAge(Integer.valueOf(age.getText().toString()));
        newStudent.setName(name.getText().toString());
        newStudent.setSex(sex);

        studentDao.insert(newStudent);

        // destroy the current activity
        finish();
    }
}
