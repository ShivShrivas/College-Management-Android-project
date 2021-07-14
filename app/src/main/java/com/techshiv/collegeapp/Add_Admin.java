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

public class Add_Admin extends AppCompatActivity {
    EditText AdimnEmailReg, AdimnNamereg, AdimnPasscodeReg, AdimnrgPhone;
    Button AdimnregBtn;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        this.setTitle("Add new Admin");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestor = FirebaseFirestore.getInstance();
        AdimnEmailReg = findViewById(R.id.AdminEmailReg);
        AdimnrgPhone = findViewById(R.id.AdminrgPhone);
        AdimnNamereg = findViewById(R.id.AdminNamereg);
        AdimnPasscodeReg = findViewById(R.id.AdminPasscodeReg);
        AdimnregBtn = (Button) findViewById(R.id.AdminregBtn);

       AdimnregBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Boolean valid=CheckAllFields();
               if(valid){
                   mFirebaseAuth.createUserWithEmailAndPassword(AdimnEmailReg.getText().toString(),AdimnPasscodeReg.getText().toString())
                           .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                               @Override
                               public void onSuccess(AuthResult authResult) {
                                   FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                                   Toast.makeText(Add_Admin.this, "Account crated", Toast.LENGTH_SHORT).show();
                                   DocumentReference documentReference= mFirebaseFirestor.collection("staffs").document(firebaseUser.getUid());
                                   Map<String,Object> staffinfo=new HashMap<>();
                                   staffinfo.put("StaffName" ,AdimnNamereg.getText().toString());
                                   staffinfo.put("Staffmail" ,AdimnEmailReg.getText().toString());
                                   staffinfo.put("StaffPhone" ,AdimnrgPhone.getText().toString());
                                   staffinfo.put("StaffPasscode" ,AdimnPasscodeReg.getText().toString());

                                   staffinfo.put("isAdmin" ,"1");
                                   documentReference.set(staffinfo);


                                   startActivity(new Intent(Add_Admin.this,AdminPanel.class));
                                   finish();
                               }
                           });
               }
           }
       });

    }
    private boolean CheckAllFields() {
        if (AdimnEmailReg.length() == 0) {
            AdimnEmailReg.setError("This field is required");
            return false;
        }

        if (AdimnPasscodeReg.length() == 0) {
            AdimnPasscodeReg.setError("This field is required");
            return false;
        }
        if (AdimnrgPhone.length() == 0) {
            AdimnrgPhone.setError("This field is required");
            return false;
        }
        if (AdimnNamereg.length() == 0) {
            AdimnNamereg.setError("This field is required");
            return false;
        }
        // after all validation return true.
        return true;
    }
    }
