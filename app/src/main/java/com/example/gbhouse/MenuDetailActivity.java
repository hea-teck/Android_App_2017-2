package com.example.gbhouse;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 김희택 on 2017-10-15.
 */
public class MenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }


        MenuDetailFragment details = new MenuDetailFragment();
        details.setSelection(getIntent().getIntExtra("index",-1));
        getSupportFragmentManager().beginTransaction().replace(R.id.details,details).commit();

//        int Img;
//        Intent intentimage = getIntent();
//        Img = intentimage.getIntExtra ("img",0);
//        ImageView image = (ImageView)findViewById(R.id.imageView);
//        image.setImageResource(Img);

//        String x1,x2,x3;
//
//        Intent intentname = getIntent();
//        x1 = intentname.getStringExtra("name");
//        TextView t1 = (TextView)findViewById(R.id.textView1);
//        t1.setText(x1);
//
//        Intent intentcost = getIntent();
//        x2 = intentcost.getStringExtra("cost");
//        TextView t2 = (TextView)findViewById(R.id.textView2);
//        t2.setText(x2);
//
//        Intent intentgrade = getIntent();
//        x3 = intentgrade.getStringExtra("grade");
//        TextView t3 = (TextView)findViewById(R.id.textView3);
//        t3.setText(x3);

    }
}