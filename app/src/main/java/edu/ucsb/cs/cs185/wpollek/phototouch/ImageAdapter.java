package edu.ucsb.cs.cs185.wpollek.phototouch;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Wesley on 2/24/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    boolean listGrid;
    public ImageAdapter(Context c) {
        BitmapManager.listener = new BitmapManager.BitListener() {
            @Override
            public void onUpdate() {
                notifyDataSetChanged();
            }
        };
        this.mContext = c;
    }


    public int getCount() {
        return BitmapManager.ITEMS.size();
    }

    public Object getItem(int position) {
        return BitmapManager.ITEMS.get(position);
    }

    public long getItemId(int position) {
        return BitmapManager.ITEMS.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView row;
        listGrid= MainActivity.listView;
        if(BitmapManager.count==0){listGrid = true;}//start with listview
        if(convertView==null){
            ImageView newv = new ImageView(mContext);
            newv.setId(BitmapManager.count);
            row=newv;
        } else {
            row = (ImageView) convertView;
        }
        if(listGrid){
            row.setLayoutParams(new ListView.LayoutParams(parent.getWidth(),parent.getHeight()/5));
            row.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else{
            int onefifth = parent.getWidth()/5;
            row.setLayoutParams(new GridView.LayoutParams(onefifth,onefifth, Gravity.CENTER));
            row.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        row.setImageBitmap(BitmapManager.ITEMS.get(position));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent imgPick = new Intent(c, ImageActivity.class);
                imgPick.putExtra("id",position);
                c.startActivity(imgPick);
            }
        });
        return row;

    }
}
