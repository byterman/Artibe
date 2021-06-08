package com.example.artibe.crear;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.artibe.R;
import com.example.artibe.inicio;

public class seleccionBlog extends AppCompatActivity
{

    ImageView imgText,imgVideo,imgImg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_blog);

        imgText = this.findViewById(R.id.img_Text);
        imgVideo = this.findViewById(R.id.img_Video);
        imgImg = this.findViewById(R.id.img_IMG);


        imgImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToIMG();
            }
        });

        imgText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToText();
            }
        });

        imgVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToVideo();
            }
        });



    }


    public void goToText() {
        Intent intent = new Intent(this, crearText.class);
        startActivity(intent);
    }
    public void goToIMG() {
        Intent intent = new Intent(this, crearIMG.class);
        startActivity(intent);

    }  public void goToVideo() {
        Intent intent = new Intent(this, crearVideo.class);
        startActivity(intent);
    }

}