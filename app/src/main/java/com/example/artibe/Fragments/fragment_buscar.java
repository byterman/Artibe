package com.example.artibe.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artibe.R;
import com.example.artibe.adapter.AdapterBusqueda;
import com.example.artibe.objetos.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class fragment_buscar extends Fragment {

    DatabaseReference db;

    SearchView searchView;
    AdapterBusqueda adapter;
    RecyclerView menubusqueda;



    /// IMAGEN DE PERFIL ARRIBA
    TextView username;
    ImageView profile;
    private FirebaseAuth mAuth;
    profile fragmentprofile = new profile();
    ///--------------
    LinearLayoutManager lm;

    ArrayList<Post> posts;

    int contador;

    public View view;

    public fragment_buscar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmet
        view = inflater.inflate(R.layout.fragment_buscar, container, false);

        db = FirebaseDatabase.getInstance().getReference().child("post");
        menubusqueda = view.findViewById(R.id.recycle_busqueda);
        searchView = view.findViewById(R.id.buscador);

        ///DECLARAR IMAGEN DE PERFIL ARRIBA
        username = view.findViewById(R.id.username2);
        profile = view.findViewById(R.id.profileIMG2);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        username.setText(currentUser.getDisplayName().toString());
        /// ESTO ESTA PUESTO COMO PLACEHOLDER, HAY QUE CAMBIARLO
        ImageView profile = view.findViewById(R.id.profileIMG2);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
        ///--------------------------------------------
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToprofile(fragmentprofile);
                Toast toast = Toast.makeText(getContext(), "Mensaje 2", Toast.LENGTH_SHORT);
                toast.show();
                // Code here executes on main thread after user presses button
            }
        });
        ///-------------------------


        lm = new LinearLayoutManager(getContext());

        menubusqueda.setLayoutManager(lm);

        posts = new ArrayList<>();
        adapter = new AdapterBusqueda(posts);
        menubusqueda.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Comprtueba si existe para que la app no explote
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        Post ms = snapshot1.getValue(Post.class);
                        posts.add(ms);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);

                return true;
            }

            private void buscar(String s) {
                ArrayList<Post>milista = new ArrayList<>();

                for (Post obj: posts){
                    if(obj.getTexto().toLowerCase().contains(s.toLowerCase())){
                        milista.add(obj);
                    }

                }
                AdapterBusqueda adapterBusqueda = new AdapterBusqueda(milista);
                menubusqueda.setAdapter(adapterBusqueda);
            }
        });

        return view;
    }
    public void goToprofile(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }


}