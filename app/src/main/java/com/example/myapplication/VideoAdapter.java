package com.example.myapplication;



import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {
    ArrayList<String> files;
    Context mContext;

    public VideoAdapter(ArrayList<String> files, Context mContext) {
        this.files = files;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView video = new ImageView(mContext);
        Uri uri = Uri.fromFile(new File(files.get(position)));
        Glide.with(mContext).load(uri).thumbnail(0.1f).into(video);
        //video.setForegroundGravity(Gravity.FILL);
        video.setLayoutParams(new GridView.LayoutParams(177,220));
        video.setVerticalScrollBarEnabled(true);
        video.setVerticalFadingEdgeEnabled(true);
        video.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return video;
    }
}
