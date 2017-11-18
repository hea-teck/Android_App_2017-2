package com.example.gbhouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    final static String TAG= "GBHouse";

    public DBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE1);
        db.execSQL(UserContract.Users.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE1);
        db.execSQL(UserContract.Users.DELETE_TABLE2);
        onCreate(db);
    }




    public long insertUserByMethod1(String name, String address, String phone) {     //picture 스트링 말고
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Users.KEY_NAME, name);
        values.put(UserContract.Users.KEY_ADDRESS, address);
        values.put(UserContract.Users.KEY_PHONE, phone);


        return db.insert(UserContract.Users.TABLE_NAME1,null,values);
    }

    public long insertUserByMethod2(String menu_name, String menu_price, String menu_explanation) {     //picture 스트링 말고
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserContract.Users.KEY_MENU_NAME, menu_name);
        values.put(UserContract.Users.KEY_MENU_PRICE, menu_price);
        values.put(UserContract.Users.KEY_MENU_EXPLANATION, menu_explanation);

        return db.insert(UserContract.Users.TABLE_NAME2,null,values);
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + UserContract.Users.TABLE_NAME1;
        return getReadableDatabase().rawQuery(sql,null);
    }

    public Cursor getAllUsersByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.Users.TABLE_NAME2,null,null,null,null,null,null);
    }
//
//    public long deleteUserByMethod(String _id) {
//        SQLiteDatabase db = getWritableDatabase();
//
//        String whereClause = UserContract.Users._ID +" = ?";
//        String[] whereArgs ={_id};
//        return db.delete(UserContract.Users.TABLE_NAME, whereClause, whereArgs);
//    }
//
//    public long updateUserByMethod(String _id, String name, String address, String phone, String picture) { //picture 스트링 말고
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(UserContract.Users.KEY_NAME, name);
//        values.put(UserContract.Users.KEY_ADDRESS, address);
//        values.put(UserContract.Users.KEY_PHONE, phone);
//        values.put(UserContract.Users.KEY_PICTURE, picture);
//
//        String whereClause = UserContract.Users._ID +" = ?";
//        String[] whereArgs ={_id};
//
//        return db.update(UserContract.Users.TABLE_NAME, values, whereClause, whereArgs);
//    }

}
