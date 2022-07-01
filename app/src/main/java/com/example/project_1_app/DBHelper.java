package com.example.project_1_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    
    public static final int DATABASE_VRSION=1;
    public DBHelper(Context context){
        super(context,"contactdb",null,DATABASE_VRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSql = "create table tb_contact (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "phone)";
        db.execSQL(tableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VRSION){
            db.execSQL("drop table tb_contact");
            onCreate(db);
        }
    }
}
