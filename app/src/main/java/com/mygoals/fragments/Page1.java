package com.mygoals.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mygoals.R;
import com.mygoals.ui.main.PageViewModel;


public class Page1 extends Fragment {


        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        private PageViewModel mViewModel;
        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;


        public Page1() {
            // Required empty public constructor
        }

        public static Page1 newInstance(String param1, String param2) {

            Page1 fragment = new Page1();
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
            mViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);


        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_page1, container, false);

            return view;
        }
    }

