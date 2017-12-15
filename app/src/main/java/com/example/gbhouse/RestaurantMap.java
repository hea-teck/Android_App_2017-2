package com.example.gbhouse;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RestaurantMap extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    final int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION = 0;
    private FusedLocationProviderClient mFusedLocationClient;
    Location mCurrentLocation;
    GoogleMap mGoogleMap;

    DBHelper1 mDbHelper1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quick_action1:

                getLastLocation(); // 현재위치를 가져온다. 추가적으로 디비와 연동하여 등록된 맛집이 존재하면 보이게 되고 없으면 보이지 않게 설정

                Cursor cursor1 = mDbHelper1.getAllUsersBySQL();

                while (cursor1.moveToNext()) {

                    //getString(1) -> 업소이름 /  getString(2) -> 주소

                        try {
                            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
                            List<Address> addresses = geocoder.getFromLocationName(cursor1.getString(2), 1);
                            if (addresses.size() > 0) {
                                Address bestResult = (Address) addresses.get(0);

                                LatLng location = new LatLng(bestResult.getLatitude(), bestResult.getLongitude());

                                mGoogleMap.addMarker(
                                        new MarkerOptions().
                                                position(location).
                                                title(cursor1.getString(2)).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.qaz))));
                               //맛집을 등록하고 다시 지도로 돌아가서 현재위치 버튼을 누르면 예전에 입력했던 데이터베이스에 저장이 되있는 getString(2) -> 주소가 적용되어 마크 모양이 설정해준것으로 바뀌게 된다.
                                mGoogleMap.setOnMarkerClickListener(this);
                            }
                        } catch (IOException e) {
                            Log.e(getClass().toString(), "Failed in using Geocoder.", e);

                        }
                }

                cursor1.close();
                return true;

            case R.id.action_subactivity1:
                return true;

            case R.id.action_subactivity2:
                return true;

            case R.id.action_subactivity3:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mDbHelper1 = new DBHelper1(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (!checkLocationPermissions()) {
            requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
        } else {
            getLastLocation();
        }
        Button btn = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.edit_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                getAddress();
            }

        });
    }

    private boolean checkLocationPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermissions(int requestCode) {
        ActivityCompat.requestPermissions(
                RestaurantMap.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                } else {
                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT);
                }
            }
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    mCurrentLocation = location;

                    LatLng newLocation = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                    if (mGoogleMap != null)
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                } else
                    Toast.makeText(getApplicationContext(),
                            "No Location Detected",
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) { //onSucess 보다 먼저 수행
        mGoogleMap = googleMap;
    }

    private void getAddress() {
        EditText address = (EditText) findViewById(R.id.edit_test);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(address.getText().toString(), 1);

            if (addresses.size() > 0) {

                Address bestResult = (Address) addresses.get(0);
                LatLng location = new LatLng(bestResult.getLatitude(), bestResult.getLongitude());

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

                mGoogleMap.addMarker(
                        new MarkerOptions().
                                position(location).
                                title(address.getText().toString()));
                mGoogleMap.setOnMarkerClickListener(this);
            }
        } catch (IOException e) {
            Log.e(getClass().toString(), "Failed in using Geocoder.", e);
            return;
        }
    }

    public boolean onMarkerClick(Marker marker) {                                       //http://webnautes.tistory.com/1094 다이얼로그 추가하는 법
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("맛집 등록");
        builder.setMessage("새로운 맛집을 등록하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        EditText editText = (EditText) findViewById(R.id.edit_test);  //인텐트 이용하여 엑티비티 넘어가고 마커위치 주소 넘긴다.
                        Intent intent = new Intent(RestaurantMap.this, RestaurantRegistrationActivity.class);
                        intent.putExtra("aaa", String.valueOf(editText.getText()));
                        startActivity(intent);

                        finish();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "아니오를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();

        return true;
    }
}
