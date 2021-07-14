package com.techshiv.collegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techshiv.collegeapp.R;

public class StudntLogin extends AppCompatActivity {
    EditText studEnroll,StulogEmail;
    Button loginstudent_BTN;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studnt_login);
        this.setTitle("Student Login");
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseFirestor=FirebaseFirestore.getInstance();
        studEnroll=findViewById(R.id.studEnroll);
        StulogEmail=findViewById(R.id.StulogEmail);
        loginstudent_BTN=findViewById(R.id.loginstudent_BTN);
        loginstudent_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid=CheckAllFields();

                if(valid==true){
                    mFirebaseAuth.signInWithEmailAndPassword(StulogEmail.getText().toString(),studEnroll.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            DocumentReference documentReference=mFirebaseFirestor.collection("Students").document(authResult.getUser().getUid());
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {

                                    openactivity_student_main();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(StudntLogin.this, "Wrong Entry fields!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
    public void openactivity_student_main() {
        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StudentPanel.class);
        startActivity(intent);
        finish();
    }
    private boolean CheckAllFields() {
        if (studEnroll.length() == 0) {
            studEnroll.setError("This field is required");
            return false;
        } if (StulogEmail.length() == 0) {
            StulogEmail.setError("This field is required");
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentFirebaseUser=mFirebaseAuth.getCurrentUser();

        if (currentFirebaseUser!=null){
            String currentFirebaseUserID = currentFirebaseUser.getUid();
            if(currentFirebaseUserID=="jbowKxW87wY7aHmeSVwOPE5Kw8J3"){
            Toast.makeText(this, "you are admin please login as admin...", Toast.LENGTH_SHORT).show();

        }else   {startActivity(new Intent(StudntLogin.this,StudentPanel.class));
            finish();}
    }}
}