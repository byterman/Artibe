package com.example.artibe.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artibe.R;
import com.example.artibe.crear.seleccionBlog;
import com.example.artibe.objetos.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class fragment_menu extends Fragment {

    DatabaseReference db;

    private ImageButton anadir;

    RecyclerView menugeneral;

    protected ArrayList<Post> posts;
    int contador;
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
        contador=0;

        // ______________QUERY__________
        db = FirebaseDatabase.getInstance().getReference("post");

        db.addListenerForSingleValueEvent(valueEventListener);

        // se declara el recycle view
        menugeneral=viewMenu.findViewById(R.id.recycle_mainmenu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        menugeneral.setLayoutManager(linearLayoutManager);
        menugeneral.setAdapter(new AdaptadorMainMenuFavoritas() );

        posts = new ArrayList<Post>();




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




   ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //posts.clear();
            if (snapshot.exists()){
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Post post = snapshot1.getValue(Post.class);
                    posts.add(post);
                    Log.e("soyunlog","tamaño de posts"+posts.size());
                    Log.e("soyunlog","tipo"+posts.get(0).tipoblog);
                }
                //menugeneral.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    // __________________________________________________________________
    // __________________________________________________________________
    // __________________________________________________________________
    // __________________________________________________________________
    // __________________________________________________________________

    private class AdaptadorMainMenuFavoritas extends RecyclerView.Adapter<AdaptadorMainMenuFavoritas.AdaptadorMainMenuHolder> {
        @NonNull
        @Override
        public AdaptadorMainMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("soyunlog","test");
            if(posts.get(contador).tipoblog==1){



                contador++;

                Log.e("soyunlog","tamaño de contador"+contador);
                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_text,parent,false));
            }else if (posts.get(contador).tipoblog==2){
                contador++;

                Log.e("soyunlog","tamaño de contador"+contador);
                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_img,parent,false));
            }else{
                contador++;


                Log.e("soyunlog","tamaño de contador"+contador);
                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_video,parent,false));
            }

        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorMainMenuHolder holder, int position) {

            holder.imprimit(position);
            holder.layoutid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        private class AdaptadorMainMenuHolder extends RecyclerView.ViewHolder {


            TextView texto,userid;
            ImageView profile,img;
            View layoutid;


            public AdaptadorMainMenuHolder(@NonNull View itemView) {
                super(itemView);
                texto = itemView.findViewById(R.id.text1);
                userid = itemView.findViewById(R.id.UserId);
                profile = itemView.findViewById(R.id.profile);
                img = itemView.findViewById(R.id.image);

                layoutid = itemView.findViewById(R.id.layoutidd);

            }


            public void imprimit(int position) {
                if(posts.get(position).tipoblog==1){
                    userid.setText(posts.get(contador).nomusuario);
                    texto.setText(posts.get(contador).texto);

                }else if(posts.get(position).tipoblog==2){
                    userid.setText(posts.get(contador).nomusuario);
                    Picasso.get().load(posts.get(contador).urlimg).into(img);
                }else{

                }
            }
        }
    }

}