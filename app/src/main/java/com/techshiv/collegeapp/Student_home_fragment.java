package com.techshiv.collegeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Student_home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Student_home_fragment extends Fragment {
        ImageSlider mImageSlider;
        List<SlideModel> mSlideModels;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Student_home_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Student_home_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Student_home_fragment newInstance(String param1, String param2) {
        Student_home_fragment fragment = new Student_home_fragment();
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
        View view= inflater.inflate(R.layout.fragment_student_home_fragment, container, false);
        mImageSlider=view.findViewById(R.id.imageSliderStud);
        mSlideModels=new ArrayList<>();
        mSlideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/college-app-e56e5.appspot.com/o/sliderImages%2FWhatsApp%20Image%202021-07-02%20at%2009.58.15%20(1).jpeg?alt=media&token=a704450c-4e32-4e0a-890a-b9ae82e052ee","Our College Building"));
        mSlideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/college-app-e56e5.appspot.com/o/sliderImages%2FWhatsApp%20Image%202021-07-02%20at%2009.58.15.jpeg?alt=media&token=ecb9a26d-0410-4a21-992d-ebb47a337e93","College Campus"));
        mSlideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/college-app-e56e5.appspot.com/o/sliderImages%2FWhatsApp%20Image%202021-07-02%20at%2010.04.03.jpeg?alt=media&token=5f7d6a85-5ccc-46dd-b897-21a86e7bce2a","College Students"));
        mImageSlider.setImageList(mSlideModels,true );
        return view;
    }
}