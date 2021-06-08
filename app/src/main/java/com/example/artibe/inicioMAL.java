package com.example.artibe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.artibe.Fragments.*;
import com.example.artibe.objetos.Post;
import com.example.artibe.objetos.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class inicioMAL extends AppCompatActivity {

    fragment_buscar fragmentBuscar = new fragment_buscar();
    fragment_menu fragmentMenu = new fragment_menu();
    fragment_tienda fragmentTienda = new fragment_tienda();


    // guardar usuario

    protected ArrayList<User> user;

    private FirebaseAuth mAuth;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_mal);


        // para guardar usuarios
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        user = new ArrayList<User>();

        db = FirebaseDatabase.getInstance().getReference().child("users");

        User users = new User(currentUser.getDisplayName().toString(),currentUser.getEmail().toString(),"https://firebasestorage.googleapis.com/v0/b/artibe-7b1a9.appspot.com/o/avatar.png?alt=media&token=f46f16c2-57e1-4f6b-9134-2636ec348bee");
        user.add(users);

        db.setValue(user);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                user.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User users = postSnapshot.getValue(User.class);
                    user.add(users);
                    //Log.i("logTest",llibre.getTitol());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });




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
