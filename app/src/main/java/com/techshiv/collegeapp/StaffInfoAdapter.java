package com.techshiv.collegeapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaffInfoAdapter extends RecyclerView.Adapter<StaffInfoAdapter.StaffdataHolder> {
Context mContext;
ArrayList<StaffDataHandler> mStaffDataHandlers;

    public StaffInfoAdapter(Context context, ArrayList<StaffDataHandler> staffDataHandlers) {
        mContext=context;
        mStaffDataHandlers=staffDataHandlers;
    }

    @Override
    public void onBindViewHolder(@NonNull StaffdataHolder holder, int position) {
        StaffDataHandler staffinfo=mStaffDataHandlers.get(position);
        holder.Stafftame.setText(staffinfo.StaffName);
        holder.StaffMobInfo.setText(staffinfo.StaffPhone);
        holder.StaffMailInfo.setText(staffinfo.Staffmail);
    }

    @Override
    public int getItemCount() {
        return mStaffDataHandlers.size();
    }


    @NonNull
    @Override
    public StaffdataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_info_card,parent,false);
        return  new StaffdataHolder(view);
            }
    public class StaffdataHolder extends RecyclerView.ViewHolder{
        TextView Stafftame,StaffMobInfo,StaffMailInfo;
        public StaffdataHolder(@NonNull View itemView) {
            super(itemView);
            Stafftame=itemView.findViewById(R.id.Stafftame);
            StaffMobInfo=itemView.findViewById(R.id.StaffMobInfo);
            StaffMailInfo=itemView.findViewById(R.id.StaffMailInfo);
        }
    }
}
