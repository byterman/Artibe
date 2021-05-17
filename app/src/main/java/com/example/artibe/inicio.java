package com.example.artibe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.artibe.Fragments.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentHome,fragmentBuscar,fragmentTienda;
    Button btnBuscar,btnHome,btnStore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio2);

        btnBuscar = findViewById(R.id.btn_Buscar);
        btnHome = findViewById(R.id.btn_home);
        btnStore = findViewById(R.id.btn_store);


        fragmentHome=new fragment_menu();
        fragmentBuscar=new fragment_buscar();
        fragmentTienda=new fragment_tienda();

        getSupportFragmentManager().beginTransaction().add(R.id.soyUnFrameLayout,fragmentHome).commit();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.soyUnFrameLayout,fragmentBuscar).commit();
                transaction.addToBackStack(null);
                // Code here executes on main thread after user presses button
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.soyUnFrameLayout,fragmentHome).commit();
                transaction.addToBackStack(null);
                // Code here executes on main thread after user presses button
            }
        });

        btnStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.soyUnFrameLayout,fragmentTienda).commit();
                transaction.addToBackStack(null);
                // Code here executes on main thread after user presses button
            }
        });
    }
}