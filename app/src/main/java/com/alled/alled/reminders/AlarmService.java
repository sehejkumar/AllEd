package com.alled.alled.reminders;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.alled.R;

public class AlarmService extends Service{

    private static final int NOTIFICATION_ID = 3;

    public AlarmService(String name){
        super();
    }

    public AlarmService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onHandleIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void onHandleIntent(Intent intent) {
        createNotificationChannel();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(),"YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher) //notification icon
                .setContentTitle("title")
                .setContentText("text")
                .setAutoCancel(true);
        Intent i = new Intent(getApplicationContext(),ReminderActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pi = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,mBuilder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void createNotificationChannel(){
        CharSequence name = "AllEd";
        String description = "Check App For Reminders";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel("AllEd",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }
}
