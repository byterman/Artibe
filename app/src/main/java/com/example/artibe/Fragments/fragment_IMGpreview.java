package com.example.artibe.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.artibe.R;
import com.example.artibe.objetos.Post;
import com.squareup.picasso.Picasso;

import java.util.List;


public class fragment_IMGpreview extends Fragment {
    ImageView imagen;
    List<Post> postbusqueda;
    String posicion;
    public fragment_IMGpreview() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__i_m_gpreview, container, false);

        imagen= v.findViewById(R.id.imagendeseada);
        Log.e("soyunlog", String.valueOf(fragment_menu.posicionamiento));
        posicion =fragment_menu.posicionamiento ;
        Picasso.get().load(posicion).into(imagen);

        return v;
    }
}