package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {
    public Context mContext;
    public ArrayList<String> music;


    public MusicAdapter(Context ctx, ArrayList<String> mMusic) {
        this.mContext = ctx;
        this.music = mMusic;
    }

    @Override
    public int getCount() {
        return music.size();
    }

    @Override
    public Object getItem(int position) {
        return music.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(mContext);
        String name = Uri.parse(music.get(position)).getLastPathSegment().replace("(MP3_128K).mp3", "");
        txt.setText(name);
        txt.setHeight(120);
        txt.setTextColor(Color.parseColor("#ffffff"));
        txt.setGravity(Gravity.FILL);
        txt.setMaxLines(1);

        txt.setPaddingRelative(15, 0,0,0);

        txt.setTextSize(18);


        txt.setVerticalScrollBarEnabled(true);
        txt.setVerticalFadingEdgeEnabled(true);
        return txt;
    }

}