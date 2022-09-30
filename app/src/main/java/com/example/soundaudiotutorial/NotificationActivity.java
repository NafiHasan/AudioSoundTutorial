package com.example.soundaudiotutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        tryCustomNotificationSound();
    }

    private void tryCustomNotificationSound()
    {
        Button getNotification = findViewById(R.id.get_notification_button);

        getNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CHANNEL_ID = "my_Channel";
                Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + getApplicationContext().getPackageName() + "/raw/iphone_ringtone.mp3");
                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH);
                    AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
                    channel.setSound(soundUri, audioAttributes);
                    manager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, CHANNEL_ID);
                builder.setContentTitle(getString(R.string.app_name));
                builder.setContentText("iPhone Ringtone Playing");
                builder.setWhen(System.currentTimeMillis());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + getApplicationContext().getPackageName() + "/raw/iphone_ringtone.mp3"));

                manager.notify(1, builder.build());
            }
        });
    }


}