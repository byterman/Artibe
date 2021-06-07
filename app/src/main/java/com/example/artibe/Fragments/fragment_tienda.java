package com.example.artibe.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.artibe.R;
import com.squareup.picasso.Picasso;


public class fragment_tienda extends Fragment {
    ImageButton image;

    public fragment_tienda() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);

        image = view.findViewById(R.id.imagetest);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/Fotos_subidas%2Fls2897cb2282pedro%20pedrocomprimido.jpg?alt=media&token=b205a56e-657d-4aae-b0f0-d57ba600835b").into(image);
        // Inflate the layout for this fragment
        return view;
    }
}