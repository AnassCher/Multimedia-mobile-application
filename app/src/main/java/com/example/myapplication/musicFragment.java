package com.example.myapplication;

import static androidx.core.content.ContextCompat.getDrawable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link musicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class musicFragment extends Fragment {
    static ArrayList<String> music = new ArrayList<>();
    static MediaPlayer mp = new MediaPlayer();
    static TextView name;
    static Button pause;
    Button prev,next;
    static Intent i;
    LinearLayout bar;
    int x;
    Thread up;

    @RequiresApi(api = Build.VERSION_CODES.Q)

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public musicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment musicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static musicFragment newInstance(String param1, String param2) {
        musicFragment fragment = new musicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_music, container, false);
        music = getAllMusic();
        ListView list = v.findViewById(R.id.MusicList);
        MusicAdapter adapter = new MusicAdapter(getActivity(), music);
        list.setAdapter(adapter);

        name = v.findViewById(R.id.name);
        prev = v.findViewById(R.id.previous);
        pause = v.findViewById(R.id.stop);
        next = v.findViewById(R.id.next);
        bar = v.findViewById(R.id.bar);






        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bar.setVisibility(View.VISIBLE);

                if(mp.isPlaying()){
                    mp.stop();
                    pause.setBackground(getDrawable(getActivity(),R.drawable.play));
                    mp.reset();

                }
                try{
                    mp.setDataSource(music.get(position));
                    mp.prepare();

                    mp.start();


                }catch(Exception e){
                    e.printStackTrace();
                }
                String n = Uri.parse(music.get(position)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                name.setText(n);
                pause.setBackground(getDrawable(getActivity(), R.drawable.stop));
                i = new Intent(getActivity(), MusicPlayer.class);
                i.putExtra("id",position);

                startActivity(i);


            }

        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = i.getExtras().getInt("id");
                if(x == 0) x = music.size()-1;
                else x = x-1;

                mp.reset();
                try {
                    mp.setDataSource(music.get(x));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                pause.setBackground(getDrawable(getActivity(),R.drawable.stop));
                String n = Uri.parse(music.get(x)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                name.setText(n);
                i.putExtra("id",x);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = i.getExtras().getInt("id");
                if(x == music.size()-1) x = 0;
                else{x = x+1;}

                mp.reset();
                try {
                    mp.setDataSource(music.get(x));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                pause.setBackground(getDrawable(getActivity(),R.drawable.stop));
                String n = Uri.parse(music.get(x)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                name.setText(n);
                i.putExtra("id",x);


            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mp.isPlaying()){
                        mp.pause();
                        pause.setBackground(getDrawable(getActivity(),R.drawable.play));
                    }
                    else {
                        mp.start();
                        pause.setBackground(getDrawable(getActivity(),R.drawable.stop));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(String path : music){
                    String l = Uri.parse(path).getLastPathSegment().replace("(MP3_128K).mp3", "");
                    if(name.getText().equals(l)){
                        x = music.indexOf(path);
                    }
                }

                i.putExtra("id",x);
                startActivity(i);
            }
        });







        return v;






    }

    @Override
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public ArrayList<String> getAllMusic() {
        ArrayList<String> files = new ArrayList<>();
        Uri uri; int columIndexData;
        Cursor cursor;
        String musicPath;
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Audio.Media.DATE_ADDED};
        String order = MediaStore.Audio.Media.DATE_ADDED;
        cursor = getActivity().getContentResolver().query(uri, projection, null, null, order+" DESC");
        columIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);


        while(cursor.moveToNext()){
            if(cursor.getString(columIndexData).contains("SnapTube Audio")){
                musicPath = cursor.getString(columIndexData);
                files.add(musicPath);
           }


        }
        return files;
    }

}