package com.example.artibe.crear;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.artibe.R;
import com.example.artibe.inicio;
import com.example.artibe.objetos.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

public class crearIMG extends AppCompatActivity {

    Button btnCargar,btnSubir;
    ImageView foto;
    int  tipo = 2;
    protected ArrayList<Post> posts;
    private FirebaseAuth mAuth;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;

    Bitmap thumb_bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_i_m_g);

        foto = findViewById(R.id.imgFoto);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        posts = new ArrayList<Post>();

        btnCargar = findViewById(R.id.btn_cargar);
        btnSubir = findViewById(R.id.btn_subir);

        // AQUI ES DONDE SE DECLARA PARA SUBIR IMG
        imgref = FirebaseDatabase.getInstance().getReference().child("post");
        storageReference = FirebaseStorage.getInstance().getReference().child("Fotos_subidas");
        cargando = new ProgressDialog(this);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CropImage.startPickImageActivity(crearIMG.this);
            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Post post = new Post(tipo,"null","null","null",currentUser.getDisplayName().toString(),currentUser.getEmail().toString());
                posts.add(post);

                imgref.setValue(posts);

                goToBack();
            }
        });

        imgref.addValueEventListener(new ValueEventListener() {
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
        Intent intent = new Intent(this, inicio.class);
        startActivity(intent);
    }



}

