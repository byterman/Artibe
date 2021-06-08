package com.example.artibe.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.artibe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;


public class fragment_profile_settings extends Fragment {

    Button btnCargar,btnSubir;
    ImageView foto;
    private FirebaseAuth mAuth;

    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;

    public fragment_profile_settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_settings, container, false);


        return v;
    }
}