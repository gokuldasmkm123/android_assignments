package com.baabtra.home.gokulfb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by home on 3/16/2017.
 */
public class mysqlitehelper extends SQLiteOpenHelper {
    public mysqlitehelper (Context context) {
        super(context, "db_fbusers", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table tbl_users(username text,password text)");
        } catch (SQLiteException e) {
            Log.d("ERROR in creating table", e.getLocalizedMessage().toString());
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("drop table if exists tbl_users");
        onCreate(db);
    }

    public boolean insert(String uname,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result=-1;
        ContentValues mycontent=new ContentValues();
        mycontent.put("username",uname);
        mycontent.put("password", password);
        try{
            result=db.insert("tbl_users",null,mycontent);
        }
        catch(SQLiteException e){
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return  true;
        }

    }
    public Cursor getAllData(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from tbl_users where username='"+uname+"'",null);
        return  res;
    }
}
