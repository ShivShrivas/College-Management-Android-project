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

public class staffRegistration extends AppCompatActivity {
        EditText StaffEmailReg,staffNamereg,staffPasscodeReg,rgPhone;
        Button regBtn;
        FirebaseAuth mFirebaseAuth;
        FirebaseFirestore mFirebaseFirestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Staff Registration");
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseFirestor=FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_staff_registration);
        StaffEmailReg=findViewById(R.id.StaffEmailReg);
        rgPhone=findViewById(R.id.rgPhone);
        staffNamereg=findViewById(R.id.staffNamereg);
        staffPasscodeReg=findViewById(R.id.staffPasscodeReg);
        regBtn=findViewById(R.id.regBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid=CheckAllFields();

                if(valid){
                    mFirebaseAuth.createUserWithEmailAndPassword( StaffEmailReg.getText().toString(),staffPasscodeReg.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                            Toast.makeText(staffRegistration.this, "Account crated", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference= mFirebaseFirestor.collection("staffs").document(firebaseUser.getUid());
                            Map<String,Object> staffinfo=new HashMap<>();
                            staffinfo.put("StaffName" ,staffNamereg.getText().toString());
                            staffinfo.put("Staffmail" ,StaffEmailReg.getText().toString());
                            staffinfo.put("StaffPhone" ,rgPhone.getText().toString());
                            staffinfo.put("StaffPasscode" ,staffPasscodeReg.getText().toString());

                            staffinfo.put("isStaff" ,"1");
                            documentReference.set(staffinfo);


                            startActivity(new Intent(staffRegistration.this,AdminPanel.class));
                            finish();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(staffRegistration.this, "Account cration faild", Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });


    }
    private boolean CheckAllFields() {
        if (StaffEmailReg.length() == 0) {
            StaffEmailReg.setError("This field is required");
            return false;
        }

        if (staffNamereg.length() == 0) {
            staffNamereg.setError("This field is required");
            return false;
        }
        if (staffPasscodeReg.length() == 0) {
            staffPasscodeReg.setError("This field is required");
            return false;
        }
                // after all validation return true.
        return true;
    }
}