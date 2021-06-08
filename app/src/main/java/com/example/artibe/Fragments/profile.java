package com.example.artibe.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.artibe.R;
import com.squareup.picasso.Picasso;


public class profile extends Fragment {

    ImageView perfil;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        perfil = v.findViewById(R.id.profile1);



        /// PLACE HOLDER HAY QUE CAMBIARLO
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(perfil);
        /// ---------------------------------------------------


        // Inflate the layout for this fragment
        return v;
    }
}