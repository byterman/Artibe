package com.example.artibe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.artibe.Fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class inicioMAL extends AppCompatActivity {

    fragment_buscar fragmentBuscar = new fragment_buscar();
    fragment_menu fragmentMenu = new fragment_menu();
    fragment_tienda fragmentTienda = new fragment_tienda();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_mal);

        BottomNavigationView navigation = findViewById(R.id.menu_navegacion);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.fragment_buscar:
                        loadFragment(fragmentBuscar);
                        return true;
                    case R.id.fragment_menu:
                        loadFragment(fragmentMenu);
                        return true;
                    case R.id.fragment_tienda:
                        loadFragment(fragmentTienda);
                        return true;
                }
                return false;
            }
        });
    }


    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }
}
