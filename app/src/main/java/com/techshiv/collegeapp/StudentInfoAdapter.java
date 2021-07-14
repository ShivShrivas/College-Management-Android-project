package com.techshiv.collegeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.StudentViewHolder> {
    Context mContext;
    ArrayList<StudentDataHandler> mStudentInfoAdapters;

    public StudentInfoAdapter(Context context, ArrayList<StudentDataHandler> studentInfoAdapters) {
        mContext = context;
        mStudentInfoAdapters = studentInfoAdapters;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.studentcard,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentDataHandler studentIInfo=mStudentInfoAdapters.get(position);
        holder.StudentName.setText(studentIInfo.StudentName);
        holder.StudentFatherName.setText(studentIInfo.StudentFatherName);
        holder.StudEnroll.setText(studentIInfo.StudentEnrollment);
        holder.StudMail.setText(studentIInfo.StudentMail);



    }

    @Override
    public int getItemCount() {
        return mStudentInfoAdapters.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView StudentName,StudentFatherName,StudEnroll,StudMail;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName=itemView.findViewById(R.id.StudentName);
            StudentFatherName=itemView.findViewById(R.id.StudentFatherName);
            StudEnroll=itemView.findViewById(R.id.StudEnroll);
            StudMail=itemView.findViewById(R.id.StudMail);
        }
    }
}
