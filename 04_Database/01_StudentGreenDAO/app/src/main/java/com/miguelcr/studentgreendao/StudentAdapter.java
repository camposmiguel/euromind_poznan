package com.miguelcr.studentgreendao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcr.studentgreendao.database.Student;

import java.util.List;

/**
 * Created by miguelcampos on 9/3/16.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    private final Context context;
    private final int layout;
    private final List<Student> values;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
        this.layout = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View layoutToShow = inflater.inflate(layout, parent, false);

        // Information of the current student
        Student currentStudent = values.get(position);

        // User interface components to set the information
        ImageView avatar = (ImageView)layoutToShow.findViewById(R.id.imageViewAvatar);
        TextView name = (TextView)layoutToShow.findViewById(R.id.textViewName);
        TextView age = (TextView)layoutToShow.findViewById(R.id.textViewAge);

        // Set the informacion in the layout
        name.setText(currentStudent.getName());
        age.setText(currentStudent.getAge()+" years old");

        if(currentStudent.getSex().equals("m")){
            avatar.setImageResource(R.drawable.ic_male);
        } else {
            avatar.setImageResource(R.drawable.ic_female);
        }

        return layoutToShow;
    }
}
