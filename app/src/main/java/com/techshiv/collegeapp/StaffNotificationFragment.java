package com.techshiv.collegeapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaffNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffNotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mRecyclerView;
    public static ProgressDialog progressDialog;
    private AdapterLatestNotificationStaff adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StaffNotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaffNotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaffNotificationFragment newInstance(String param1, String param2) {
        StaffNotificationFragment fragment = new StaffNotificationFragment();
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
        progressDialog =new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data fetching...");
        progressDialog.show();
        View view= inflater.inflate(R.layout.fragment_staff_notification, container, false);
        mRecyclerView=view.findViewById(R.id.StaffNotifiationRecView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        FirebaseRecyclerOptions<notiificationDatamodel> options = new FirebaseRecyclerOptions.Builder<notiificationDatamodel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("StaffNotification"), notiificationDatamodel.class)
                .build();
        adapter = new AdapterLatestNotificationStaff(options);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        binding = FragmentLatestFreagBinding.inflate(inflater,container,false);
//        View view=binding.getRoot();

        return view;

    }

    // Read from the database



    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }

    }
}