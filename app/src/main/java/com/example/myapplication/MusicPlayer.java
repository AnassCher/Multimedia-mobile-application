package com.example.myapplication;

import static androidx.core.content.ContextCompat.getDrawable;
import static com.example.myapplication.musicFragment.mp;
import static com.example.myapplication.musicFragment.music;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayer extends AppCompatActivity {
    TextView name, duration, current;
    Button prev, play, next;
    int counter;
    static String n =null;
    SeekBar progress;
    Thread update;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        Intent i =getIntent();
        int position = i.getExtras().getInt("id");
        counter = position;

        progress = findViewById(R.id.progress);
        prev = findViewById(R.id.previous);
        play = findViewById(R.id.stop);
        next = findViewById(R.id.next);
        name = findViewById(R.id.name);
        duration = findViewById(R.id.duration);
        current = findViewById(R.id.current);
        n = Uri.parse(music.get(counter)).getLastPathSegment().replace("(MP3_128K).mp3", "");
        name.setText(n);


        update = new Thread(){
            @Override
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition =0;

                while(currentPosition<=totalDuration){
                    try{
                        sleep(500);
                        currentPosition = mp.getCurrentPosition();
                        progress.setProgress(currentPosition);
                        if(currentPosition >= totalDuration){
                            mp.reset();
                            if(counter == music.size()-1) counter = 0;
                            else counter = counter +1;

                            try {
                                mp.setDataSource(music.get(counter));
                                mp.prepare();
                                mp.start();

                                n = Uri.parse(music.get(counter)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                                name.setText(n);
                                String ti = time(mp.getDuration());
                                duration.setText(ti);
                                progress.setMax(mp.getDuration());
                                progress.setProgress(mp.getCurrentPosition());
                                currentPosition = mp.getCurrentPosition();
                                totalDuration = mp.getDuration();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }
        };
        progress.setMax(mp.getDuration());
        update.start();
        progress.getProgressDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
        progress.getThumb().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(progress.getProgress());

            }
        });

        String Duration = time(mp.getDuration());
        duration.setText(Duration);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = time(mp.getCurrentPosition());
                current.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        }, delay);



        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.reset();
                if(counter == 0) counter = music.size()-1;
                else counter--;

                try{

                    mp.setDataSource(music.get(counter));
                    mp.prepare();
                    mp.start();
                    play.setBackground(getDrawable(R.drawable.stop));
                    n = Uri.parse(music.get(counter)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                    name.setText(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                musicFragment.name.setText(n);
                String Duration = time(mp.getDuration());
                progress.setMax(mp.getDuration());
                progress.setProgress(mp.getCurrentPosition());
                duration.setText(Duration);
                Animation anima = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                name.setAnimation(anima);


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.reset();
                if(counter == music.size()-1) counter = 0;
                else counter++;


                try{

                    mp.setDataSource(music.get(counter));
                    mp.prepare();
                    mp.start();
                    play.setBackground(getDrawable(R.drawable.stop));
                    n = Uri.parse(music.get(counter)).getLastPathSegment().replace("(MP3_128K).mp3", "");
                    name.setText(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                musicFragment.name.setText(n);
                String Duration = time(mp.getDuration());
                progress.setMax(mp.getDuration());
                progress.setProgress(mp.getCurrentPosition());
                duration.setText(Duration);


            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mp.isPlaying()){
                        mp.pause();
                        play.setBackground(getDrawable(R.drawable.play));
                        musicFragment.pause.setBackground(getDrawable(R.drawable.play));
                    }
                    else {
                        mp.start();
                        play.setBackground(getDrawable(R.drawable.stop));
                        musicFragment.pause.setBackground(getDrawable(R.drawable.stop));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                Animation anima = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                name.setAnimation(anima);
                i.putExtra("id",counter);

            }
        });






    }

    public String time(int duration){
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        time+= min+":";
        if(sec<10){
            time+="0";
        }
        time+=sec;
        return time;
    }
}