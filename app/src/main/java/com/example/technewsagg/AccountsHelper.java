package com.example.technewsagg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountsHelper extends SQLiteOpenHelper {
    public static final String databaseName="UserAccounts.db";
    public static final String tableName="Accounts";
    public static final String usernameColumn="Username";
    public static final String passwordColumn="Password";


    public AccountsHelper(@Nullable Context context) {
        super(context,databaseName,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+" (Username TEXT PRIMARY KEY,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public boolean createAccount(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(usernameColumn,username);
        contentValues.put(passwordColumn,password);
        long result=db.insert(tableName,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean usernameExists(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+tableName+ " Where Username=\""+username+"\"",null);
        if(res.getCount()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean validAccount(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " +tableName+ " Where Username=\"" +username+ "\" AND Password =\"" +password+"\"",null);
        if(res.getCount()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
