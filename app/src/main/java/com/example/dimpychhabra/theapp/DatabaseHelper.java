package com.example.dimpychhabra.theapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.math.BigInteger;

/**
 * Created by Dimpy Chhabra on 3/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String Database_Name = "data.db";
    public static final String Table_Name = "user";
    public static final String Col_1 = "_id";
    public static final String Col_2="name";
    public static final String Col_3="num";
    public static final String Col_4="email";
    public static final String Col_5="password";
    final String createTable=" create table " + Table_Name +
            "(_id integer primary key autoincrement, name text, num text , email text, password text)";
    static SQLiteDatabase db;
    public DatabaseHelper(Context context){
        super(context ,Database_Name ,null,1);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(createTable);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXIST" + Table_Name);
        onCreate(db);
    }
    public boolean insertData(String name, String num, String email, String password)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,num);
        contentValues.put(Col_4,email);
        contentValues.put(Col_5,password);
        long result = db.insert(Table_Name,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


    public boolean checkData(String email,String password){
        String emailInput = email;
        String passInput = password;
        Log.e(" email >"+emailInput+"< "," and pword is >>"+passInput+"<");
        db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT * FROM user", null);
        if (cursor1.getCount()!=0) {
            cursor1.moveToFirst();
            String eml = cursor1.getString(3);
            String pswrd = cursor1.getString(4);
            Log.e(" Fetching for "+eml, " on  check :::::" + pswrd);
        }
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE TRIM(email)= '"+email.trim()+"'", null);
        Log.e(" in neither zero >"+emailInput+"< "," and pword is >>"+passInput+"<");
        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            String pswrd = cursor.getString(4);
            Log.e(" Fetching ", " on  check :::::" + pswrd);
            Log.e(" in not zero "," and here on will check");
            if(pswrd.equals(password)){
                return true;
            }
        }db.close();
        return false;
    }
}
