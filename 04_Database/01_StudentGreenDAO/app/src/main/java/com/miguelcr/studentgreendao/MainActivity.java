package com.miguelcr.studentgreendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcr.studentgreendao.database.DaoSession;
import com.miguelcr.studentgreendao.database.Student;
import com.miguelcr.studentgreendao.database.StudentDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoSession dbSession;
    private StudentDao studentDao;
    private List<Student> listaStudents;
    private ListView listView;
    private StudentAdapter adapter;
    private boolean activityLaunched = false;

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
                Intent i = new Intent(MainActivity.this,NewStudentActivity.class);
                startActivity(i);
                activityLaunched = true;
            }
        });

        // Database Connection
        dbSession = DatabaseConnection.getConnection(this);
        studentDao = dbSession.getStudentDao();

        // ListView

        // 1. list of students from db
        listaStudents = studentDao.loadAll();

        // 2. Adapter
        adapter = new StudentAdapter(this,R.layout.student_item_list,listaStudents);

        // 3. Connect ListView and Adapter
        listView.setAdapter(adapter);

        // 4. Connect the ListView with de context menu
        registerForContextMenu(listView);
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

    @Override
    protected void onResume() {
        super.onResume();

        if(activityLaunched) {
            // refresh the listview
            listaStudents.clear();
            listaStudents = studentDao.loadAll();
            adapter.notifyDataSetChanged();
            activityLaunched = false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_students, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit_student:
                Toast.makeText(MainActivity.this, "Edit "+listaStudents.get(info.position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_student:
                Toast.makeText(MainActivity.this, "Delete "+listaStudents.get(info.position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
