package com.example.artibe.crear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.artibe.R;
import com.example.artibe.inicio;
import com.example.artibe.inicioMAL;
import com.example.artibe.objetos.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class crearVideo extends AppCompatActivity {
    Button btnvide;

    int  tipo = 3;
    protected EditText Url;
    protected ArrayList<Post> posts;

    private FirebaseAuth mAuth;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_video);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        posts = new ArrayList<Post>();

        Url = findViewById(R.id.EditUrl);
        btnvide = findViewById(R.id.btn_video);

        db = FirebaseDatabase.getInstance().getReference().child("post");


        btnvide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Post post = new Post(tipo,"null",Url.getText().toString(),"null",currentUser.getDisplayName().toString(),currentUser.getEmail().toString());
                posts.add(post);

                db.setValue(posts);

                goToBack();
            }
        });


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                posts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    posts.add(post);
                    //Log.i("logTest",llibre.getTitol());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });


    }


    public void goToBack() {
        Intent intent = new Intent(this, inicioMAL.class);
        startActivity(intent);
    }



}