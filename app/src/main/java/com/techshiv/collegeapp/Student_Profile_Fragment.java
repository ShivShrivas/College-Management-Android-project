package com.techshiv.collegeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Student_Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Student_Profile_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView StudentNamePro,FatherNamePro,EnrollmentPro,EmailPro;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestor;
    String studId;
    Context mContext;
    MaterialButton logoutStudBtn;

    public Student_Profile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Student_Profile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Student_Profile_Fragment newInstance(String param1, String param2) {
        Student_Profile_Fragment fragment = new Student_Profile_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student__profile_, container, false);
        StudentNamePro=view.findViewById(R.id.StudentNamePro);
        FatherNamePro=view.findViewById(R.id.FatherNamePro);
        EnrollmentPro=view.findViewById(R.id.EnrollmentPro);
        EmailPro=view.findViewById(R.id.EmailPro);
        logoutStudBtn=view.findViewById(R.id.logoutStudBtn);
        logoutStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                
                startActivity(new Intent(getContext(),MainActivity.class));
                Toast.makeText(getActivity(), "Logged Out... Please login", Toast.LENGTH_SHORT).show();

            }
        });
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseFirestor=FirebaseFirestore.getInstance();
        studId=mFirebaseAuth.getCurrentUser().getUid();
        AppCompatActivity appCompatActivity= new AppCompatActivity();
        DocumentReference documentReference=mFirebaseFirestor.collection("Students").document(studId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    StudentNamePro.setText(documentSnapshot.getString("StudentName"));
                    FatherNamePro.setText(documentSnapshot.getString("StudentFatherName"));
                    EnrollmentPro.setText(documentSnapshot.getString("StudentEnrollment"));
                    EmailPro.setText(documentSnapshot.getString("StudentMail"));
                }
                else {
                    Toast.makeText(getContext(), "profile not found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed to fetcg data", Toast.LENGTH_SHORT).show();
            }
        });
//        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                StudentNamePro.setText(value.getString("StudentName"));
//                FatherNamePro.setText(value.getString("StudentFatherName"));
//                EnrollmentPro.setText(value.getString("StudentEnrollment"));
//                EmailPro.setText(value.getString("StudentMail"));
//            }
//        });
        return view;
    }

}