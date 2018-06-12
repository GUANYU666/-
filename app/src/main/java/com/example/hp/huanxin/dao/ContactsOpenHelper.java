package com.example.hp.huanxin.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsOpenHelper extends SQLiteOpenHelper {
    private ContactsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ContactsOpenHelper(Context context)
    {
        this(context,"contacts.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table t_contact (_id integer primary key,username varchar(20),contact varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
