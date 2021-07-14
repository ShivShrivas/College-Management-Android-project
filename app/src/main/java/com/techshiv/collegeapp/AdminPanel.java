package com.techshiv.collegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminPanel extends AppCompatActivity {
    CardView registerStaff,registerStudent,StudentInfo,UploadNews,UploadNotification,StaffInfo,addadmin,logoutAdmin;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        this.setTitle("Admin Panel");
        registerStaff=findViewById(R.id.registerStaff);
        registerStudent=findViewById(R.id.registerStudent);
        StudentInfo=findViewById(R.id.StudentInfo);
        addadmin=findViewById(R.id.addadmin);
        logoutAdmin=findViewById(R.id.logoutAdmin);
        StaffInfo=findViewById(R.id.StaffInfo);
        UploadNews=findViewById(R.id.UploadNews);
        UploadNotification=findViewById(R.id.uploadNotification);

        registerStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,staffRegistration.class);
                startActivity(i);
            }
        });
        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,StudentRegistration.class);
                startActivity(i);
            }
        });
        StudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,StudentIInfo.class);
                startActivity(i);
            }
        });
        StaffInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,StaffInfo.class);
                startActivity(i);
            }
        });
        UploadNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,UploadNewsAdmin.class);
                startActivity(i);
            }
        });
        UploadNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,UploadNotificationAdmin.class);
                startActivity(i);
            }
        });
        logoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(AdminPanel.this,MainActivity.class));
                Toast.makeText(AdminPanel.this, "Logged Out... Please login", Toast.LENGTH_SHORT).show();

            }
        });
         addadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPanel.this,Add_Admin.class);
                startActivity(i);


            }
        });



    }
}