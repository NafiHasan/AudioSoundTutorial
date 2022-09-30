package com.example.soundaudiotutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundEffectActivity extends AppCompatActivity {

    // soundpool variables
    private SoundPool soundPool;
    private int sound1, sound2, sound3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_effect);

        configureSoundEffects();
        configureBackButton();
    }

    private void configureSoundEffects(){
        // potential Error
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes. USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }
        sound1 = soundPool.load(this, R.raw.sound1, 1);
        sound2 = soundPool.load(this, R.raw.sound2, 1);
        sound3 = soundPool.load(this, R.raw.sound3, 1);
        playSound();
    }

    public void playSound(){
        Button effect1 = (Button) findViewById(R.id.effect1);
        effect1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                soundPool.play(sound1, 0, 1, 1, 0, 1);
            }
        });

        Button effect2 = (Button) findViewById(R.id.effect2);
        effect2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                soundPool.play(sound2, 1, 1, 1, 0, 1);
            }
        });

        Button effect3 = (Button) findViewById(R.id.effect3);
        effect3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                soundPool.play(sound3, 1, 1, 1, 0, 1);
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    private void configureBackButton(){
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}