package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class StartActivity extends AppCompatActivity {

    VideoView videoView;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        videoView = (VideoView) findViewById(R.id.videoView);
        button = (Button) findViewById(R.id.nextActivity);

        String videoPath = "android.resource://" + getPackageName() + "/raw/shop_video";
        //Uri uri = Uri.parse(videoPath);
        //videoView.setVideoURI(uri);
        videoView.setVideoPath(videoPath);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer){
                button.setVisibility(View.VISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startMainActivity();
            }
        });
        videoView.start();
    }
    private void startMainActivity(){
        Intent intent = new Intent(this, Login_Activity.class);
        startActivity(intent);
        finish();
    }
}