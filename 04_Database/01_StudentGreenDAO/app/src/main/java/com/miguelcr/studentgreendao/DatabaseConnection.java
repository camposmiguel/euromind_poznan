package com.miguelcr.studentgreendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.miguelcr.studentgreendao.database.DaoMaster;
import com.miguelcr.studentgreendao.database.DaoSession;

/**
 * Created by miguelcampos on 10/3/16.
 */
public class DatabaseConnection {
    private static SQLiteDatabase db;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoSession getConnection(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx,
                "student-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        return daoSession;
    }
}
