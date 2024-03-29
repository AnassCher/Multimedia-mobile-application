package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {
    public Context mContext;
    public ArrayList<String> img;


    public GalleryAdapter(Context ctx, ArrayList<String> mImg) {
        this.mContext = ctx;
        this.img = mImg;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(mContext);
        Uri uri = Uri.fromFile(new File(img.get(position)));
        Glide.with(mContext).load(uri).thumbnail(0.1f).into(image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image.setLayoutParams(new GridView.LayoutParams(177,220));
        image.setVerticalScrollBarEnabled(true);
        image.setVerticalFadingEdgeEnabled(true);
        return image;
    }

}
