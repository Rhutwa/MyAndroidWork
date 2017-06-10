package com.example.samir.pushnotificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("Async Task completed");
        builder.setContentText("This is demo for push notifications");
        builder.setTicker("This is ticker");
        builder.setSmallIcon(android.R.drawable.star_on);

        /*Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        builder.setContentIntent(pi);*/
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(123, builder.build());
    }
}
