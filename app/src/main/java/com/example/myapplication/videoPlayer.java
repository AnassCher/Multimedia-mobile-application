package com.example.myapplication;

import static com.example.myapplication.videoFragment.files;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class videoPlayer extends AppCompatActivity {
    VideoView video  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        int position = getIntent().getExtras().getInt("pos");
        Toast.makeText(this, ""+files.get(59), Toast.LENGTH_SHORT).show();
        video = findViewById(R.id.videoView);
        video.setVideoURI(Uri.parse(files.get(position)));
        MediaController media = new MediaController(this);
        video.setMediaController(media);
        media.setAnchorView(video);

    }
}