package com.example.soundaudiotutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class VirtualAudio extends AppCompatActivity {
    private Button play;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_audio);

        play= findViewById(R.id.link1);

        mediaPlayer= new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://penguinradio.dominican.edu/Sound%20FX%20Collection/Cat%20Meow.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mplayer) {
                Toast.makeText(VirtualAudio.this, "Ready to play", Toast.LENGTH_SHORT).show();
                mplayer.start();
            }
        });

        mediaPlayer.prepareAsync();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
    }
}