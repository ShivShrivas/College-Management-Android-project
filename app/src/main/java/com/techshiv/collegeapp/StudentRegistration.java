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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistration extends AppCompatActivity {
    EditText NameStuReg,FatherNameStuReg,EnrollStuReg,StudentMailReg;
    Button stuRegBtn;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        this.setTitle("Student Registration");
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseFirestor=FirebaseFirestore.getInstance();
        NameStuReg=findViewById(R.id.NameStuReg);
        FatherNameStuReg=findViewById(R.id.FatherNameStuReg);
        EnrollStuReg=findViewById(R.id.EnrollStuReg);
        stuRegBtn=findViewById(R.id.stuRegBtn);
        StudentMailReg=findViewById(R.id.StudentMailReg);

        stuRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid=CheckAllFields();

                if(valid){
                    mFirebaseAuth.createUserWithEmailAndPassword( StudentMailReg.getText().toString(),EnrollStuReg.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                            DocumentReference documentReference= mFirebaseFirestor.collection("Students").document(firebaseUser.getUid());
                            Map<String,Object> StudentInfo=new HashMap<>();
                            StudentInfo.put("StudentName" ,NameStuReg.getText().toString());
                            StudentInfo.put("StudentMail" ,StudentMailReg.getText().toString());
                            StudentInfo.put("StudentFatherName" ,FatherNameStuReg.getText().toString());
                            StudentInfo.put("StudentEnrollment" ,EnrollStuReg.getText().toString());

                            documentReference.set(StudentInfo);

                            Toast.makeText(StudentRegistration.this, "Student add", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(StudentRegistration.this,AdminPanel.class));
                            finish();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(StudentRegistration.this, "Account cration faild", Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });
    }
    private boolean CheckAllFields() {
        if (NameStuReg.length() == 0) {
            NameStuReg.setError("This field is required");
            return false;
        }

        if (FatherNameStuReg.length() == 0) {
            FatherNameStuReg.setError("This field is required");
            return false;
        }
        if (EnrollStuReg.length() == 0) {
            EnrollStuReg.setError("This field is required");
            return false;
        }
        // after all validation return true.
        return true;
    }
}