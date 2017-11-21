package com.example.gbhouse;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RestaurantDetailActivity extends AppCompatActivity {
    static MyAdapter adapter;

    final static String TAG="SQLITEDBTEST";

    EditText m_menu_name;
    EditText m_menu_price;
    EditText m_menu_explanation;

    private DBHelper1 mDbHelper1;
    private DBHelper2 mDbHelper2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        m_menu_name = (EditText) findViewById(R.id.edit_menu_name);
        m_menu_price = (EditText) findViewById(R.id.edit_menu_price);
        m_menu_explanation = (EditText) findViewById(R.id.edit_menu_explanation);

        mDbHelper1 = new DBHelper1(this);
        mDbHelper2 = new DBHelper2(this);

        viewAllToTextView();
        viewAllToListView();
    }


    private void viewAllToTextView() {
        TextView result1 = (TextView) findViewById(R.id.textView1);
        TextView result2 = (TextView) findViewById(R.id.textView3);
        TextView result3 = (TextView) findViewById(R.id.textView4);

        ImageView result4 = (ImageView) findViewById(R.id.imageView1);



        Cursor cursor1 = mDbHelper1.getAllUsersBySQL();

        while (cursor1.moveToNext()) {
            result4.setImageURI(Uri.parse(cursor1.getString(4)));
            result1.setText(cursor1.getString(1));
            result2.setText(cursor1.getString(2));
            result3.setText(cursor1.getString(3));


        }
    }


    private void viewAllToListView() {

        Cursor cursor2 = mDbHelper2.getAllUsersByMethod();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.item, cursor2, new String[]{
                UserContract2.Users.KEY_MENU_IMAGEURI,
                UserContract2.Users.KEY_MENU_NAME,
                UserContract2.Users.KEY_MENU_PRICE,
                },
                new int[]{R.id.imageView, R.id.textView1, R.id.textView2}, 0);

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Adapter adapter = adapterView.getAdapter();
//
//                m_menu_name .setText(((Cursor)adapter.getItem(i)).getString(0));
//                m_menu_price.setText(((Cursor)adapter.getItem(i)).getString(1));
//                m_menu_explanation.setText(((Cursor)adapter.getItem(i)).getString(2));
//            }
//        });
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }






//
//        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent implicit_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:027416888"));
//                startActivity(implicit_intent);
//            }
//        });
//
//        final ArrayList<MyItem> data = new ArrayList<MyItem>();
//        data.add(new MyItem(R.drawable.picture3, "햄치즈휠렛빅버거", "3,900"));
//
//        adapter = new MyAdapter(this, R.layout.item, data);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View vClicked, int position, long id) {
//
//                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);
//
//                intent.putExtra("img", data.get(position).mImage);
//                intent.putExtra("name", data.get(position).nFoodname);
//                intent.putExtra("cost", data.get(position).nFoodprice);
//
//                if(position==0){
//                    String grade1 = "평점 3.7";
//                    intent.putExtra("grade", grade1);
//                }
//                else if(position==1){
//                    String grade2 = "평점 2.5";
//                    intent.putExtra("grade", grade2);
//                }
//                else if(position==2){
//                    String grade3 = "평점 4.2";
//                    intent.putExtra("grade", grade3);
//                }
//                else if(position==3){
//                    String grade4 = "평점 3.1";
//                    intent.putExtra("grade", grade4);
//                }
//                startActivity(intent);
//
//            }
//        });
//

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.plus:
                startActivity(new Intent(this, menuselectActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


