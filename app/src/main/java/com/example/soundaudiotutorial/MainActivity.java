package com.example.soundaudiotutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSoundActivityButton();
        configureCustomSoundButton();
    }

    private void configureSoundActivityButton(){
        Button next = (Button) findViewById(R.id.trySoundButton);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SoundEffectActivity.class));
            }
        });
    }

    private void configureCustomSoundButton()
    {
        Button customSoundActivity = findViewById(R.id.tryCustomNotificationSound);

        customSoundActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });
    }
}