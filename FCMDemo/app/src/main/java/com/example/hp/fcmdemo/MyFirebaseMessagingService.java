package com.example.hp.fcmdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by Hp on 13-05-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    private CharSequence body;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getData().get("message"));
    }
    private void showNotification(String message)
    {
        Intent intent=new Intent(MyFirebaseMessagingService.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pintent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
       
        builder.setContentTitle("message")
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setTicker("TickerTitle")
                //.setContentTitle("Firebase Message")
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pintent).getNotification();
        //Notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(getRandomNumber(),builder.build());
    }
    public int getRandomNumber()
    {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        return randomInt;
    }
}
