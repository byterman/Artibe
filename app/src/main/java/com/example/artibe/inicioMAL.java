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





        /*
        navigation.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) mOnNavigationItemSelectedListener);
    */

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });
    }
/*
    private final BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_buscar:
                    loadFragment(fragmentBuscar);
                case R.id.fragment_menu:
                    loadFragment(fragmentMenu);
                case R.id.fragment_tienda:
                    loadFragment(fragmentTienda);
            }
        }
    };
*/

    public void selectFragment(MenuItem item) {
        Log.d("FragmentsItemID","EEEEEEEEEEEEEEEEEEEEEEEEEEEEEHHHHHHHHHH  "+item);
        if (item.toString()=="Buscar") {
            Log.d("FragmentsItemID", "itemid" + item.getItemId());
            Log.d("Fragments", "fragmentBuscar");
            Log.d("FragmentsItemID", "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEHHHHHHHHHH  " + item);
            loadFragment(fragmentBuscar);
        } else if (item.toString()=="Home") {
            Log.d("FragmentsItemID", "itemid" + item.getItemId());
            Log.d("Fragments", "fragmentMenu");
            Log.d("FragmentsItemID", "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEHHHHHHHHHH  " + item);
            loadFragment(fragmentMenu);
        } else if (item.toString()=="Store") {
            Log.d("FragmentsItemID", "itemid" + item.getItemId());
            Log.d("Fragments", "fragmentTienda");
            Log.d("FragmentsItemID", "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEHHHHHHHHHH  " + item);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Log.d("Fragments","estoy cargando el fragment:"+fragmentTienda);
            transaction.replace(R.id.fragment_general, fragmentTienda);
            transaction.commit();
            loadFragment(fragmentTienda);


        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Log.d("Fragments","estoy cargando el fragment:"+fragment);
        transaction.replace(R.id.fragment_general, fragment);
        transaction.commit();
    }

}