package com.example.gbhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 김희택 on 2017-10-17.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<MyItem> mItems = new ArrayList<MyItem>();

    public MyAdapter(Context context, int resource, ArrayList<MyItem> items) {
        mContext = context;
        mItems = items;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        image.setImageResource(mItems.get(position).mIcon);


        TextView foodname = (TextView) convertView.findViewById(R.id.textView1);
        foodname .setText(mItems.get(position).nName);


        TextView foodprice = (TextView) convertView.findViewById(R.id.textView2);
        foodprice.setText(mItems.get(position).nAge);

        return convertView;
    }
}

class MyItem {
    int mIcon;
    String nName;
    String nAge;

    MyItem(int aIcon, String aName, String aAge) {
        mIcon = aIcon;
        nName = aName;
        nAge = aAge;
    }
}
