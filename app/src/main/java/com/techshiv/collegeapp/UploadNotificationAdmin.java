package com.techshiv.collegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.techshiv.collegeapp.NotificationChannel.CHANNEL1;
import static com.techshiv.collegeapp.NotificationChannel.CHANNEL2;

public class UploadNotificationAdmin extends AppCompatActivity {

    private NotificationManagerCompat mNotificationManagerCompat;
    private EditText mEditTextTitle,mEditTextMessage;
    DatabaseReference studentRefrence,StaffRefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notification_admin);
        this.setTitle("Upload Notification");
        mNotificationManagerCompat=NotificationManagerCompat.from(this);
        mEditTextTitle=findViewById(R.id.TitleNotification);
        mEditTextMessage=findViewById(R.id.DescriptionNotification);


    }
    public void sendToStudent(View v){
        String title=mEditTextTitle.getText().toString();
        String message=mEditTextMessage.getText().toString();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,CHANNEL1)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_notifcation)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
        insertnotificationStudent(title,message);

    }

    private void insertnotificationStudent(String NotificationTitle,String NotificationMessage) {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE ,dd-MM-yyyy hh:mm:ss a");
        String datetime =simpleDateFormat.format(calendar.getTime());
        studentRefrence= FirebaseDatabase.getInstance().getReference().child("StudentNotification");
        notiificationDatamodel notiificationDatamodel= new notiificationDatamodel(NotificationMessage,NotificationTitle,datetime);
        studentRefrence.push().setValue(notiificationDatamodel);
        Toast.makeText(this, "Student's notofication inserted", Toast.LENGTH_SHORT).show();
    }

    public void sendToStaff(View v){
        String title=mEditTextTitle.getText().toString();
        String message=mEditTextMessage.getText().toString();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,CHANNEL1)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_notifcation)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
        insertnotificationStaff(title,message);
    }
    private void insertnotificationStaff(String NotificationTitle,String NotificationMessage) {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE ,dd-mm-yyyy hh:mm:ss a");
        String datetime =simpleDateFormat.format(calendar.getTime());
        studentRefrence= FirebaseDatabase.getInstance().getReference().child("StaffNotification");
        notiificationDatamodel notiificationDatamodel= new notiificationDatamodel(NotificationMessage,NotificationTitle,datetime);
        studentRefrence.push().setValue(notiificationDatamodel);
        Toast.makeText(this, "Staff's notofication inserted", Toast.LENGTH_SHORT).show();
    }

}