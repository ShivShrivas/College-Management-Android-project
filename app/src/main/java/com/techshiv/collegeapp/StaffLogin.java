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

import org.w3c.dom.Document;

public class StaffLogin extends AppCompatActivity {
    EditText staffEmail,staffpasscode;
        Button StaffLogin;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseFirestor=FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_staff_login);
        this.setTitle("Staff Login");
        staffEmail=findViewById(R.id.staffEmail);
        staffpasscode=findViewById(R.id.staffpasscode);
        StaffLogin=(Button) findViewById(R.id.StaffLoginbtn);
      StaffLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Boolean valid=CheckAllFields();

              if(valid){
                 mFirebaseAuth.signInWithEmailAndPassword(staffEmail.getText().toString(),staffpasscode.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                     @Override
                     public void onSuccess(AuthResult authResult) {
                       checkAccessLevel(authResult.getUser().getUid());
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(StaffLogin.this, "Wrong Entry fields!!", Toast.LENGTH_SHORT).show();
                     }
                 });
              }

          }
      });

    }

    private void checkAccessLevel(String uid) {
        DocumentReference documentReference=mFirebaseFirestor.collection("staffs").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.getString("isStaff")!=null){
                    startActivity(new Intent(StaffLogin.this,StaffPanel.class));
                    finish();
                }else{
                    if(documentSnapshot.getString("isAdmin")!=null){
                        startActivity(new Intent(StaffLogin.this,AdminPanel.class));
                        finish();

                    }
                }
            }
        });
    }

    private void openactivity_management_main() {
        Intent intent = new Intent(this,StaffPanel.class);
        startActivity(intent);
    }
    private boolean CheckAllFields() {
        if (staffEmail.length() == 0) {
            staffEmail.setError("This field is required");
            return false;
        }

        if (staffpasscode.length() == 0) {
            staffpasscode.setError("This field is required");
            return false;
        }


        // after all validation return true.
        return true;
    }
    protected void onStart() {
        super.onStart();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        if (currentFirebaseUser!=null)
        {
            String currentFirebaseUserID = currentFirebaseUser.getUid();
            if (currentFirebaseUserID.equals("jbowKxW87wY7aHmeSVwOPE5Kw8J3")){
                startActivity(new Intent(StaffLogin.this,AdminPanel.class));
                Toast.makeText(this, "Welcome Admin..", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                startActivity(new Intent(StaffLogin.this,StaffPanel.class));
                Toast.makeText(this, "Welcome sir...", Toast.LENGTH_SHORT).show();
                finish();}
        }else Toast.makeText(this, "Please login...", Toast.LENGTH_LONG).show();


    }
}