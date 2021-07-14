package com.techshiv.collegeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StaffInfo extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<StaffDataHandler> mStaffDataHandlers=new ArrayList<>();
    FirebaseFirestore mFirebaseFirestor;
    StaffInfoAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info);
        this.setTitle("All Faculity Info");
        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data fetching...");
        progressDialog.show();
        mRecyclerView=findViewById(R.id.StaffInfoAdminRecView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseFirestor=FirebaseFirestore.getInstance();
        mStaffDataHandlers= new ArrayList<>(mStaffDataHandlers);
        adapter=new StaffInfoAdapter(StaffInfo.this,mStaffDataHandlers);
        mRecyclerView.setAdapter(adapter);

        studentInfoListener();



    }

    private void studentInfoListener() {
        mFirebaseFirestor.collection("staffs").orderBy("StaffName")
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
                                mStaffDataHandlers.add(documentChange.getDocument().toObject(StaffDataHandler.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}