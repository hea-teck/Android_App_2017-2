package com.example.gbhouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by B10542 on 2017-11-17.
 */

public class menuselectActivity extends AppCompatActivity {

    final static String TAG="GBHouse";

    EditText m_menu_name;
    EditText m_menu_price;
    EditText m_menu_explanation;
    ImageButton  m_menu_Picture;

    private DBHelper mDbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select);

        m_menu_name = (EditText) findViewById(R.id.edit_menu_name);
        m_menu_price = (EditText) findViewById(R.id.edit_menu_price);
        m_menu_explanation = (EditText) findViewById(R.id.edit_menu_explanation);
        m_menu_Picture = (ImageButton) findViewById(R.id.imageButton4);

        mDbHelper = new DBHelper(this);

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        Button button = (Button) findViewById(R.id.veiwall_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
                startActivity(intent);
                insertRecord();
            }
        });
    }

    private void insertRecord() {
        EditText menu_name = (EditText) findViewById(R.id.edit_menu_name);
        EditText menu_price = (EditText) findViewById(R.id.edit_menu_price);
        EditText menu_explanation = (EditText) findViewById(R.id.edit_menu_explanation);
        ImageButton  menu_Picture = (ImageButton) findViewById(R.id.imageButton4);

        long nOfRows = mDbHelper.insertUserByMethod(menu_name.getText().toString(), menu_price.getText().toString(), menu_explanation.getText().toString(), menu_Picture .getImageMatrix().toString());
        if (nOfRows > 0)
            Toast.makeText(this, "메뉴가 등록되었습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

                ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
                imageButton4.setImageURI(Uri.fromFile(mPhotoFile));
            }
        }
    }

    String mPhotoFileName;
    File mPhotoFile;

    final int REQUEST_IMAGE_CAPTURE = 100;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG" + currentDateFormat() + ".jpg";
            mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

            if (mPhotoFile != null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.gbhouse", mPhotoFile);

                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }
}
