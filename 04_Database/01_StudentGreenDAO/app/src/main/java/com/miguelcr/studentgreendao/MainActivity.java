package com.miguelcr.studentgreendao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.miguelcr.studentgreendao.database.DaoSession;
import com.miguelcr.studentgreendao.database.Student;
import com.miguelcr.studentgreendao.database.StudentDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoSession dbSession;
    private StudentDao studentDao;
    private List<Student> listaStudents;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Visual components
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listViewStudents);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Database Connection
        dbSession = DatabaseConnection.getConnection(this);
        studentDao = dbSession.getStudentDao();

        // ListView

        // 1. list of students from db
        listaStudents = studentDao.loadAll();

        // 2. Adapter
        StudentAdapter adapter = new StudentAdapter(this,
                R.layout.student_item_list,listaStudents);

        // 3. Connect ListView and Adapter
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
