package com.example.artibe.crear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artibe.R;
import com.example.artibe.inicio;

public class crearIMG extends AppCompatActivity {

    Button btnIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_i_m_g);



        btnIMG = findViewById(R.id.btn_IMG);

        btnIMG.setOnClickListener(new View.OnClickListener() {
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

