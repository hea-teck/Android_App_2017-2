package com.example.gbhouse;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by 김희택 on 2017-11-21.
 */

public class RestaurantDetailFragment extends Fragment{

    int mCurCheckPosition = -1;

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int i);
    }

    public RestaurantDetailFragment(){
    }

    public DBHelper1 mDbHelper1;
    public DBHelper2 mDbHelper2;

    EditText m_menu_name;
    EditText m_menu_price;
    EditText m_menu_explanation;
    ImageButton  m_menu_Picture;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // 화면에 보일 뷰 만듬

        final View rootView = (View) inflater.inflate(R.layout.activity_main, container, false);  // 뷰 객체 만들어줌

        m_menu_name = (EditText) rootView.findViewById(R.id.edit_menu_name);
        m_menu_price = (EditText) rootView.findViewById(R.id.edit_menu_price);
        m_menu_explanation = (EditText) rootView.findViewById(R.id.edit_menu_explanation);
        m_menu_Picture = (ImageButton) rootView.findViewById(R.id.imageButton4);

        mDbHelper1 = new DBHelper1(getActivity());

        TextView result1 = (TextView) rootView.findViewById(R.id.textView1);
        TextView result2 = (TextView) rootView.findViewById(R.id.textView3);
        TextView result3 = (TextView) rootView.findViewById(R.id.textView4);
        ImageView result4 = (ImageView) rootView.findViewById(R.id.imageView1);

        Cursor cursor1 = mDbHelper1.getAllUsersBySQL();

        while (cursor1.moveToNext()) {
            result4.setImageURI(Uri.parse(cursor1.getString(4)));
            result1.setText(cursor1.getString(1));
            result2.setText(cursor1.getString(2));
            result3.setText(cursor1.getString(3));
        }


        mDbHelper2 = new DBHelper2(getActivity());
        Cursor cursor2 = mDbHelper2.getAllUsersByMethod();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.item, cursor2, new String[]{
                UserContract2.Users.KEY_MENU_IMAGEURI,
                UserContract2.Users.KEY_MENU_NAME,
                UserContract2.Users.KEY_MENU_PRICE,
                },
                new int[]{R.id.imageView, R.id.textView1, R.id.textView2}, 0);

        ListView lv = (ListView)rootView.findViewById(R.id.listView);
        lv.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", -1);
            if (mCurCheckPosition >= 0) {
                Activity activity = getActivity(); // activity associated with the current fragment
                ((OnTitleSelectedListener)activity).onTitleSelected(mCurCheckPosition);

                ListView lv = (ListView) getView().findViewById(R.id.listView);
                lv.setSelection(mCurCheckPosition);
                lv.smoothScrollToPosition(mCurCheckPosition);
            }
        }
    }

    //    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

}
