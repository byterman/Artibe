package com.example.artibe.Fragments;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artibe.R;
import com.example.artibe.crear.seleccionBlog;
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

public class fragment_menu extends Fragment {

    DatabaseReference db;

    private ImageButton anadir;

    RecyclerView menugeneral;

    public static String posicionamiento;

    TextView username;
    private FirebaseAuth mAuth;
    profile fragmentprofile = new profile();


    fragment_IMGpreview fragmentIMG = new fragment_IMGpreview();

    protected ArrayList<Post> posts;
    int contador;

    public View viewMenu;

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
        viewMenu = inflater.inflate(R.layout.fragment_menu, container, false);
        contador=0;

        posts = new ArrayList<Post>();
        username = viewMenu.findViewById(R.id.username2);


        //=======PARA EL PERFIL DE ARRIBA==========
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        username.setText(currentUser.getDisplayName().toString());

        //=============================

        // ______________QUERY__________
        db = FirebaseDatabase.getInstance().getReference("post");

        db.addListenerForSingleValueEvent(valueEventListener);







        /// ESTO ESTA PUESTO COMO PLACEHOLDER, HAY QUE CAMBIARLO
        ImageView profile = viewMenu.findViewById(R.id.profileIMG2);
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

    public void goToprofile(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }




   ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //posts.clear();
            if (snapshot.exists()){
                int count = 0;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Post post = snapshot1.getValue(Post.class);
                    posts.add(post);
                    Log.e("soyunlog","tama??o de posts"+posts.size());
                    Log.e("soyunlog","tipo"+posts.get(count).tipoblog);
                    count ++;
                }
                //menugeneral.notifyDataSetChanged();

                Log.e("soyunlog", "---> " + posts.size());
                menugeneral= viewMenu.findViewById(R.id.recycle_mainmenu);
                menugeneral.setAdapter(new AdaptadorMainMenuFavoritas());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                menugeneral.setLayoutManager(linearLayoutManager);

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

        public int position;
        public int contador = 0;

        @NonNull
        @Override
        public AdaptadorMainMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("soyunlog","test");

            if(posts.get(contador).getTipoblog()==1){
                Log.e("soyunlog", "HELLOOOO SOY ITM TEXT " + posts.get(contador).tipoblog);
                Log.e("soyunlog","tama??o de contador"+contador);

                contador ++;


                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_text,parent,false));

            }else if (posts.get(contador).getTipoblog()==2){
                Log.e("soyunlog", "HELLOOOO SOY ITM IMG " + posts.get(contador).tipoblog);
                Log.e("soyunlog","tama??o de contador "+contador);

                contador ++;


                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_img,parent,false));

            }else{
                Log.e("soyunlog", "HELLOOOO SOY ITM video  " + posts.get(contador).tipoblog);
                Log.e("soyunlog","tama??o de contador"+contador);

                contador ++;
                return new AdaptadorMainMenuHolder(getLayoutInflater().inflate(R.layout.item_video,parent,false));
            }
        }

        public void goToImg(Fragment fragment) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_general, fragment);
            transaction.commit();
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorMainMenuHolder holder, int position) {
            holder.imprimit(position);
            holder.layoutid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                }
            });

            if(posts.get(position).tipoblog==2){
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                        posicionamiento = posts.get(position).urlimg;
                        goToImg(fragmentIMG);
                    }
                });
            }


        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        private class AdaptadorMainMenuHolder extends RecyclerView.ViewHolder {

            TextView texto,userid;
            ImageView profile,img;
            View layoutid;
            YouTubePlayerView youtube;

            public AdaptadorMainMenuHolder(@NonNull View itemView) {
                super(itemView);
                texto = itemView.findViewById(R.id.text1);
                userid = itemView.findViewById(R.id.UserId);
                profile = itemView.findViewById(R.id.profile);
                img = itemView.findViewById(R.id.image);
                layoutid = itemView.findViewById(R.id.layoutidd);

                youtube = itemView.findViewById(R.id.youtube_player_view);

            }


            public void imprimit(int position) {
                Log.e("soyunlog", "---------------_> " + position);
                if(posts.get(position).tipoblog==1){
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                    userid.setText(posts.get(position).nomusuario);
                    texto.setText(posts.get(position).texto);

                }else if(posts.get(position).tipoblog==2){
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                    userid.setText(posts.get(position).nomusuario);
                    Picasso.get().load(posts.get(position).urlimg).into(img);
                }else{
                    Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                    userid.setText(posts.get(position).nomusuario);

                    youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            String videoId = posts.get(position).url;
                            youTubePlayer.loadVideo(videoId, 0);
                        }
                    });

                }

                //"https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/Fotos_subidas%2Fls2897cb2282pedro%20pedrocomprimido.jpg?alt=media&token=b205a56e-657d-4aae-b0f0-d57ba600835b"
            }
        }
    }

}