package com.example.artibe.Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.artibe.R;
import com.example.artibe.crear.seleccionBlog;
import com.example.artibe.inicio;

public class fragment_menu extends Fragment {

    private ImageButton anadir;

    public fragment_menu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        ImageButton anadir = viewMenu.findViewById(R.id.imageView2);

        anadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goTo();
                Toast toast = Toast.makeText(getContext(), "Mensaje 2", Toast.LENGTH_SHORT);
                toast.show();
                // Code here executes on main thread after user presses button
            }
        });
        // Inflate the layout for this fragment
        return viewMenu;
    }
    public void goTo() {


        Intent intent = new Intent(getContext(), seleccionBlog.class);
        startActivity(intent);
    }
}