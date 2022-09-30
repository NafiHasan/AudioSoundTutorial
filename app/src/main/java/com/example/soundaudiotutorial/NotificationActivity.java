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
                String CHANNEL_ID = "1234";
                Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + getApplicationContext().getPackageName() + "/" + R.raw.iphone_ringtone);
                NotificationManager nManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH);
                    channel.setLightColor(Color.BLUE);
                    channel.enableLights(true);

                    channel.setDescription(" ");

                    AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
                    channel.setSound(soundUri, audioAttributes);

                    if(nManager != null)
                    {
                        nManager.createNotificationChannel(channel);
                    }

                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this, CHANNEL_ID);
                builder.setContentText("My Notification");
                builder.setWhen(System.currentTimeMillis());
                builder.setContentTitle(getString(R.string.app_name));
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + getApplicationContext().getPackageName() + "/" + R.raw.iphone_ringtone));
                builder.setAutoCancel(true);


                //NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotificationActivity.this);
                nManager.notify(1, builder.build());
            }
        });
    }


}