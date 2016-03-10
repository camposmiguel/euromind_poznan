package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DatabaseGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.miguelcr.studentgreendao.database");

        addStudent(schema);

        new DaoGenerator().generateAll(schema, "../01_StudentGreenDAO/app/src/main/java");
    }


    private static void addStudent(Schema schema) {
        Entity student = schema.addEntity("Student");
        student.addIdProperty().primaryKey().autoincrement();
        student.addStringProperty("name");
        student.addIntProperty("age");
        student.addStringProperty("sex");

    }
}
