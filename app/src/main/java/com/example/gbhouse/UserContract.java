package com.example.gbhouse;

import android.provider.BaseColumns;

/**
 * Created by 김희택 on 2017-11-17.
 */

public class UserContract {
    public static final String DB_NAME="restaurant.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserContract() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME="Restaurant";
        public static final String KEY_NAME = "Name";
        public static final String KEY_ADDRESS = "ADDRESS";
        public static final String KEY_PHONE = "PHONE";
        public static final String KEY_PICTURE = "PICTURE";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +

                " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_ADDRESS + TEXT_TYPE + COMMA_SEP +
                KEY_PHONE + TEXT_TYPE + COMMA_SEP +
                KEY_PICTURE + TEXT_TYPE +
                " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}