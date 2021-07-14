package com.techshiv.collegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button student;
    private Button management;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("GP Madhogarh");
        student=(Button) findViewById(R.id.btnstudent);
        management=(Button) findViewById(R.id.btncollge);
        management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StaffLogin.class);
                student.setVisibility(View.GONE);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StudntLogin.class);
                management.setVisibility(View.GONE);

                startActivity(intent);

            }
        });

    }




}