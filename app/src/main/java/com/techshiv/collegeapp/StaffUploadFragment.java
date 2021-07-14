package com.techshiv.collegeapp;

import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.techshiv.collegeapp.NotificationChannel.CHANNEL1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaffUploadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffUploadFragment extends Fragment {
    private NotificationManagerCompat mNotificationManagerCompat;
    private EditText mEditTextTitle,mEditTextMessage;
    DatabaseReference studentRefrence,StaffRefrence;
    Button SentToStudentStaff;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StaffUploadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaffUploadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaffUploadFragment newInstance(String param1, String param2) {
        StaffUploadFragment fragment = new StaffUploadFragment();
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
        View view= inflater.inflate(R.layout.fragment_staff_upload, container, false);
        mNotificationManagerCompat=NotificationManagerCompat.from(getContext());
        mEditTextTitle=view.findViewById(R.id.TitleNotificationStaff);
        mEditTextMessage=view.findViewById(R.id.DescriptionNotificationStaff);
        SentToStudentStaff=view.findViewById(R.id.SentToStudentStaff);
        SentToStudentStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mEditTextTitle.getText().toString();
                String message=mEditTextMessage.getText().toString();
                NotificationCompat.Builder builder= new NotificationCompat.Builder(getContext(),CHANNEL1)
                        .setContentTitle(title)
                        .setSmallIcon(R.drawable.ic_notifcation)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setAutoCancel(true)
                        .setContentText(message);
                NotificationManagerCompat managerCompat=NotificationManagerCompat.from(getContext());
                managerCompat.notify(999,builder.build());
                insertnotificationStudent(title,message);
            }
        });
        return view;

    }

    private void insertnotificationStudent(String NotificationTitle,String NotificationMessage) {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE ,dd-mm-yyyy hh:mm:ss a");
        String datetime =simpleDateFormat.format(calendar.getTime());
        studentRefrence= FirebaseDatabase.getInstance().getReference().child("StudentNotification");
        notiificationDatamodel notiificationDatamodel= new notiificationDatamodel(NotificationMessage,NotificationTitle,datetime);
        studentRefrence.push().setValue(notiificationDatamodel);
        Toast.makeText(getContext(), "Student's notofication inserted", Toast.LENGTH_SHORT).show();
    }
}