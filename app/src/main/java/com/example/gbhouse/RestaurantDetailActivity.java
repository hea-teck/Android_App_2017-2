package com.example.gbhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {
    static MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        final ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.picture3, "햄치즈휠렛빅버거", "  3,900"));
        data.add(new MyItem(R.drawable.picture4, "핫스파이스빅버거", "  3,500"));
        data.add(new MyItem(R.drawable.picture5, "양념치킨", "              15,900"));
        data.add(new MyItem(R.drawable.picture6, "데리야끼 치킨", "      15,900"));

        adapter = new MyAdapter(this, R.layout.item, data);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked, int position, long id) {

            }
        });

    }

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

}