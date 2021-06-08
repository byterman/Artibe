package com.example.artibe.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artibe.Fragments.fragment_menu;
import com.example.artibe.R;
import com.example.artibe.objetos.Post;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterBusqueda extends RecyclerView.Adapter<AdapterBusqueda.viewholderbusqueda> {

    List<Post> postbusqueda;
    int contador=0;

    public AdapterBusqueda(List<Post> postbusqueda) {
        this.postbusqueda = postbusqueda;
    }

    @NonNull
    @Override
    public viewholderbusqueda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);;
        viewholderbusqueda holder = new viewholderbusqueda(v);

        if(postbusqueda.get(contador).getTipoblog()==1){
            Log.e("soyunlog", "HELLOOOO SOY ITM TEXT " + postbusqueda.get(contador).tipoblog);
            Log.e("soyunlog","tamaño de contador"+contador);

            contador ++;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
            holder = new viewholderbusqueda(v);
        }else if (postbusqueda.get(contador).getTipoblog()==2){
            Log.e("soyunlog", "HELLOOOO SOY ITM IMG " + postbusqueda.get(contador).tipoblog);
            Log.e("soyunlog","tamaño de contador "+contador);

            contador ++;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false);
            holder = new viewholderbusqueda(v);
        }else{
            Log.e("soyunlog", "HELLOOOO SOY ITM video  " + postbusqueda.get(contador).tipoblog);
            Log.e("soyunlog","tamaño de contador"+contador);

            contador ++;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
            holder = new viewholderbusqueda(v);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderbusqueda holder, int position) {

        holder.imprimit(position);
        //Post ms = postbusqueda.get(position);

    }

    @Override
    public int getItemCount() {
        return postbusqueda.size();
    }

    public class viewholderbusqueda extends RecyclerView.ViewHolder {

        TextView texto,userid;
        ImageView profile,img;
        View layoutid;
        YouTubePlayerView youtube;


        public viewholderbusqueda(@NonNull View itemView) {
            super(itemView);

            texto = itemView.findViewById(R.id.text1);
            userid = itemView.findViewById(R.id.UserId);
            profile = itemView.findViewById(R.id.profile);
            img = itemView.findViewById(R.id.image);
            youtube = itemView.findViewById(R.id.youtube_player_view);
            layoutid = itemView.findViewById(R.id.layoutidd);
        }

        public void imprimit(int position) {
            if(postbusqueda.get(position).tipoblog==1){
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                userid.setText(postbusqueda.get(position).nomusuario);
                texto.setText(postbusqueda.get(position).texto);

            }else if(postbusqueda.get(position).tipoblog==2){
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                userid.setText(postbusqueda.get(position).nomusuario);
                Picasso.get().load(postbusqueda.get(position).urlimg).into(img);
            }else{
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee").into(profile);
                userid.setText(postbusqueda.get(position).nomusuario);
                youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = postbusqueda.get(position).url;
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }
    }
}
