package com.example.hp.huanxin.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.huanxin.dao.ContactsOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    private static ContactsOpenHelper scontactsOpenHelper;
    private static boolean inited = false;
    public static void init(Context context)
    {
        scontactsOpenHelper = new ContactsOpenHelper(context);
        inited = true;
    }
    public static void savaContact(String username,List<String> contacts)
    {
        if(!inited)
        {
            throw new RuntimeException("还未初始化，请先初始化");
        }
        SQLiteDatabase writableDatabase = scontactsOpenHelper.getWritableDatabase();

        writableDatabase.beginTransaction();

        writableDatabase.delete("t_contact","username=?",new String[]{username});
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        for (int i = 0;i<contacts.size();i++)
        {
            contentValues.put("contact",contacts.get(i));
            writableDatabase.insert("t_contact",null,contentValues);
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
        writableDatabase.close();
    }
    public static List<String> getCountacts(String username)
    {
        List<String> contactList = new ArrayList<>();

        SQLiteDatabase readableDatabase = scontactsOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("t_contact", new String[]{"contact"}, "username=?", new String[]{username}, null, null, "contact");
         while (cursor.moveToNext())
         {
             String cursorString = cursor.getString(0);
             contactList.add(cursorString);
         }
         cursor.close();
        return  contactList;
    }

}