package com.example.artibe.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artibe.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class profile extends Fragment {

    ImageView perfil,logout;
    TextView usernick;
    private FirebaseAuth mAuth;
    fragment_profile_settings fragmentsetings = new fragment_profile_settings();

    private GoogleSignInClient mGoogleSignInClient;

    public profile() {
        // Required empty public constructor
    }

    public void goToSetings(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }
    public void goTo(){
        Intent intent = new Intent(getContext(), com.example.artibe.logout.class);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        perfil = v.findViewById(R.id.profileclick);

        perfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSetings(fragmentsetings);
            }
        });

        usernick = v.findViewById(R.id.UserId);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        usernick.setText(currentUser.getDisplayName().toString());

        logout = v.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goTo();
            }
        });


        /// PLACE HOLDER HAY QUE CAMBIARLO
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(perfil);
        /// ---------------------------------------------------


        // Inflate the layout for this fragment
        return v;
    }


}