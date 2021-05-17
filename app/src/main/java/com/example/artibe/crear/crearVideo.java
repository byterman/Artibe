package com.example.artibe.crear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artibe.R;
import com.example.artibe.inicio;

public class crearVideo extends AppCompatActivity {
    Button btnvide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_video);

        btnvide = findViewById(R.id.btn_video);

        btnvide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToBack();
            }
        });
    }


    public void goToBack() {
        Intent intent = new Intent(this, inicio.class);
        startActivity(intent);
    }
}