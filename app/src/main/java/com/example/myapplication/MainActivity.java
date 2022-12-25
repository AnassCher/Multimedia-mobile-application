package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView name;
    ImageView userim;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);










        DrawerLayout drawer = findViewById(R.id.drawerLayout);

        ImageView image = findViewById(R.id.imageV);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navi = findViewById(R.id.navigationView);
        navi.setItemIconTintList(null);
        View na = navi.getHeaderView(0);

        //change name and image of user
        String j = null;
        int i =0;
        Bundle b = getIntent().getExtras();
        if(b != null){
            j = (String) b.getString("name");
            //Toast.makeText(getApplicationContext(), ""+j, Toast.LENGTH_SHORT).show();
            name  = (TextView) na.findViewById(R.id.Username);
            name.setText(j);

            i = (int) b.getInt("image");
            userim = (ImageView) na.findViewById(R.id.imageProf);
            userim.setImageResource(i);
            userim.setScaleType(ImageView.ScaleType.FIT_XY);

        }



        NavController nav = Navigation.findNavController(this, R.id.NavHostFragement);
        NavigationUI.setupWithNavController(navi, nav);

        TextView title = findViewById(R.id.title);

        nav.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                  title.setText(navDestination.getLabel());
                  if(navDestination.getId()==R.id.dec){
                      startActivity(new Intent(getApplicationContext(), login.class));
                  }
            }
        });







    }




}