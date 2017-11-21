package com.example.gbhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper1 extends SQLiteOpenHelper {

    final static String TAG= "GBHouse";

    public DBHelper1(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE1);
        onCreate(db);
    }

    public long insertUserByMethod1(String name, String address, String phone, String imageuri) {     //picture 스트링 말고
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Users.KEY_NAME, name);
        values.put(UserContract.Users.KEY_ADDRESS, address);
        values.put(UserContract.Users.KEY_PHONE, phone);
        values.put(UserContract.Users.KEY_PICTURE, imageuri);

        return db.insert(UserContract.Users.TABLE_NAME1,null,values);
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + UserContract.Users.TABLE_NAME1;
        return getReadableDatabase().rawQuery(sql,null);
    }


}