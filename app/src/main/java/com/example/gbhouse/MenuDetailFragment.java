package com.example.gbhouse;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 김희택 on 2017-11-21.
 */

public class MenuDetailFragment extends android.support.v4.app.Fragment {

    public DBHelper2 mDbHelper2;
    static int index = -1;

    public MenuDetailFragment(){
    }

    public void setSelection(int i){index = i;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate layout for this fragment
        View view = inflater.inflate(R.layout.activity_menu_detail, container, false);
        mDbHelper2 = new DBHelper2(getActivity());

        Cursor Cursor = mDbHelper2.getAllUsersByMethod();
        Intent intent = new Intent();

        int select = intent.getIntExtra("title",index);
        Cursor.moveToPosition(select);




        TextView tv1 = (TextView)view.findViewById(R.id.textView1);
        tv1.setText(Cursor.getString(1));

        TextView tv2 = (TextView)view.findViewById(R.id.textView2);
        tv2.setText(Cursor.getString(2));

        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        img.setImageURI(Uri.parse(Cursor.getString(3)));

        TextView tv3 = (TextView)view.findViewById(R.id.textView3);
        tv3.setText(Cursor.getString(4));



        return view;


    }

}
