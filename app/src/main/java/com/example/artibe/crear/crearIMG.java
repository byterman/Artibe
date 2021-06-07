package com.example.artibe.crear;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.artibe.R;
import com.example.artibe.inicio;
import com.example.artibe.objetos.Post;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import id.zelory.compressor.Compressor;

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




        posts = new ArrayList<Post>();

        btnCargar = findViewById(R.id.btn_cargar);
        btnSubir = findViewById(R.id.btn_subir);

        // AQUI ES DONDE SE DECLARA PARA SUBIR IMG
        imgref = FirebaseDatabase.getInstance().getReference().child("post");
        storageReference = FirebaseStorage.getInstance().getReference().child("Fotos_subidas");
        cargando = new ProgressDialog(this);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // solo va en dispositivo fisico por algun motivo

                CropImage.startPickImageActivity(crearIMG.this);

                /*
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(crearIMG.this);

                 */
                /*
                CropImage.activity(imageUri)
                        .start(this);

                 */
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageuri = CropImage.getPickImageResultUri(this,data);

            //esto recorta

            CropImage.activity(imageuri).setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(640,480)
                    .setAspectRatio(2,1).start(crearIMG.this);
        }
        //comprueba si se ha recortado
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();

                File url = new File(resultUri.getPath());

                Picasso.get().load(url).into(foto);

                // esto comrpime

                try {
                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(90)
                            .compressToBitmap(url);
                }catch (IOException e){
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
                final  byte [] thumb_byte = byteArrayOutputStream.toByteArray();


                // fin de la compresion y ahi en el thumb_byte ya tenemos la IMG para subirla a firebase

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                btnSubir.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {



                        cargando.setTitle("subiendo foto...");
                        cargando.setMessage("Espera");
                        cargando.show();


                        int p = (int) (Math.random() * 25 + 1);int s= (int) (Math.random()* 25 +1) ;
                        int t = (int) (Math.random() * 25 + 1);int c= (int) (Math.random()* 25 +1) ;

                        int numero1 = (int) (Math.random() * 1012 + 2111);
                        int numero2 = (int) (Math.random() * 1012 + 2111);

                        String[] elementos = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

                        final String aleatorio = elementos[p] + elementos[s] +
                                numero1 + elementos[t] + elementos[c] + numero2 + currentUser.getDisplayName().toString()+"comprimido.jpg";

                        final StorageReference ref =storageReference.child(aleatorio);
                        UploadTask uploadTask = ref.putBytes(thumb_byte);


                        // se sube la imagen a storage

                        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                                if (!task.isSuccessful()){
                                    throw Objects.requireNonNull(task.getException());
                                }
                                return ref.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                //ESTE SE SUPONE QUE ES EL LINK DE LA IMAGEN
                                Uri downloaduri = task.getResult();

                                Post post = new Post(tipo,"null","null",downloaduri.toString(),currentUser.getDisplayName().toString(),currentUser.getEmail().toString());
                                posts.add(post);

                                imgref.setValue(posts);
                            }
                        });


                        /*
                        Post post = new Post(tipo,"null","null","null",currentUser.getDisplayName().toString(),currentUser.getEmail().toString());
                        posts.add(post);

                        imgref.setValue(posts);
                        */
                        //goToBack();
                    }
                });
            }
        }


    }

    public void goToBack() {
        Intent intent = new Intent(this, inicio.class);
        startActivity(intent);
    }



}

