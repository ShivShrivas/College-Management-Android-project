package com.techshiv.collegeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class

StudentIInfo extends AppCompatActivity {
        RecyclerView mRecyclerView;
   ArrayList<StudentDataHandler> mStudentDataHandlers=new ArrayList<>();
    FirebaseFirestore mFirebaseFirestor;
    StudentInfoAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_i_info);
        this.setTitle("All Student Information");
        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data fetching...");
        progressDialog.show();
        mRecyclerView=findViewById(R.id.StudentinfoRecycller);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseFirestor=FirebaseFirestore.getInstance();
        mStudentDataHandlers= new ArrayList<>(mStudentDataHandlers);
        adapter=new StudentInfoAdapter(StudentIInfo.this,mStudentDataHandlers);
        mRecyclerView.setAdapter(adapter);

        studentInfoListener();



    }

    private void studentInfoListener() {
        mFirebaseFirestor.collection("Students").orderBy("StudentEnrollment", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error!=null){
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.d("error acccur",error.getMessage());
                        return;
                    }
                    for (DocumentChange documentChange:value.getDocumentChanges()){
                        if (documentChange.getType()==DocumentChange.Type.ADDED){
                            mStudentDataHandlers.add(documentChange.getDocument().toObject(StudentDataHandler.class));
                        }
                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                    }
                });
    }
}