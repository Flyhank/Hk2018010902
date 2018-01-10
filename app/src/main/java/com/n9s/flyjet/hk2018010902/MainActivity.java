package com.n9s.flyjet.hk2018010902;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    String idLove = "LOVE";
    NotificationChannel channelLove;
    NotificationManager nm;
    final int Notification_ID = 321321; //取消鍵用

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //channelLove = new NotificationChannel(idLove, "Channel Love", NotificationManager.IMPORTANCE_HIGH);
        //channelLove.setDescription("最重要的人");
        //channelLove.enableLights(true);
        //channelLove.enableVibration(true);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //nm.createNotificationChannel(channelLove);
        if(Build.VERSION.SDK_INT >=26)
        {
            regChannel();   //註冊一個channel
        }
    }

    @TargetApi(Build.VERSION_CODES.O)   //O: Oreo, Android 8
    public void regChannel()
    {
        channelLove = new NotificationChannel(idLove, "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);
        nm.createNotificationChannel(channelLove);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation")
    public void click1(View v)
    {
        //Notification.Builder builder = new Notification.Builder(MainActivity.this, idLove);
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder = new Notification.Builder(MainActivity.this, idLove);
        }
        else
        {
            builder = new Notification.Builder(MainActivity.this);
        }

        Intent it = new Intent(MainActivity.this, infoActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 123, it,
                PendingIntent.FLAG_UPDATE_CURRENT); //requestCode:取消用



        builder.setContentTitle("測試");
        builder.setContentText("這是測試內容");
        //builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        //builder.setSmallIcon(R.mipmap.ic_launcher);
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        else
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        builder.setAutoCancel(true);
        builder.setContentIntent(pi);

        Notification notify = builder.build();
        nm.notify(Notification_ID, notify);
    }

    public void click2(View v)
    {
        nm.cancel(Notification_ID);
    }
}
