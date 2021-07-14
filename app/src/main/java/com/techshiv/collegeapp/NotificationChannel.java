package com.techshiv.collegeapp;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

public class NotificationChannel extends Application {
    public static final String CHANNEL1 = "Student";
    public static final String CHANNEL2 = "Staff";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            android.app.NotificationChannel channel1=new android.app.NotificationChannel(CHANNEL1,"channle 1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Notifiaction for Studnet");
            android.app.NotificationChannel channel2=new android.app.NotificationChannel(CHANNEL2,"channle 1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Notifiaction for Staff");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }



}
