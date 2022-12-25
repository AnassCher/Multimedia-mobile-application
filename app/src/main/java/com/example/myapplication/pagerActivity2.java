package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class pagerActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager2);



        Intent i = getIntent();
        ArrayList<String> files = i.getExtras().getStringArrayList("list");
        int x= i.getExtras().getInt("id");
        ViewPager pager = findViewById(R.id.pager);
        Adapter adapter = new Adapter(this, files);
        pager.setAdapter(adapter);
        pager.setCurrentItem(x);

    }
}