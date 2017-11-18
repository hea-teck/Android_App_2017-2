package com.example.gbhouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class RestaurantDetailActivity extends AppCompatActivity {
//    final static String TAG="SQLITEDBTEST";

//    EditText mName;
//    EditText mAddress;
//    EditText mPhone;
//
//    private DBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
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
//        data.add(new MyItem(R.drawable.picture4, "핫스파이스빅버거", "3,500"));
//        data.add(new MyItem(R.drawable.picture5, "크리스피 치킨", "13,900"));
//        data.add(new MyItem(R.drawable.picture6, "데리야끼 치킨","15,900"));
//
//        adapter = new MyAdapter(this, R.layout.item, data);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);
//
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




