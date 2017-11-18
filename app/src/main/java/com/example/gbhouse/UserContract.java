package com.example.gbhouse;

import android.provider.BaseColumns;

import static android.webkit.WebView.HitTestResult.IMAGE_TYPE;

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

        public static final String TABLE_NAME1="Restaurant";
        public static final String KEY_NAME = "Name";
        public static final String KEY_ADDRESS = "ADDRESS";
        public static final String KEY_PHONE = "PHONE";
        public static final String KEY_PICTURE = "PICTURE";//사진 스트링 말고...

        public static final String TABLE_NAME2="Restaurant";
        public static final String KEY_MENU_NAME = "MENU_NAME";
        public static final String KEY_MENU_PRICE = "MENU_PRICE";
        public static final String KEY_MENU_EXPLANATION = "MENU_EXPLANATION";


        public static final String CREATE_TABLE1 = "CREATE TABLE " + TABLE_NAME1 +

                " (" +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_ADDRESS + TEXT_TYPE + COMMA_SEP +
                KEY_PHONE + TEXT_TYPE + COMMA_SEP +
                KEY_PICTURE + IMAGE_TYPE +

                " )";

        public static final String DELETE_TABLE1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;




        public static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 +

                " (" +
                KEY_MENU_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_MENU_PRICE + TEXT_TYPE + COMMA_SEP +
                KEY_MENU_EXPLANATION + TEXT_TYPE +
                " )";

        public static final String DELETE_TABLE2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
    }

}