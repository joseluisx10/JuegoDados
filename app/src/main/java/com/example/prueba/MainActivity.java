package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String email;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        ImageView dado1;
        ImageView dado2;
        ImageView dado3;
        ImageView dado4;
        ImageView dado5;
        Button pulsar;
        LinearLayout ll;
        TextView username;

        {
            ll = findViewById(R.id.linearLayout);
            username = new TextView(ll.getContext());
            dado1 = findViewById(R.id.img1);
            dado2 = findViewById(R.id.img2);
            dado3 = findViewById(R.id.img3);
            dado4 = findViewById(R.id.img4);
            dado5 = findViewById(R.id.img5);
            pulsar = findViewById(R.id.button3);
            ll.addView(username);

        }

        //downloadOnClick();
        pulsar.setOnClickListener(new View.OnClickListener() {
            private Integer[] imagenesID = new Integer[]{R.drawable.dado1, R.drawable.dado2, R.drawable.dado3, R.drawable.dado4, R.drawable.dado5};

            @Override
            public void onClick(View view) {

                username.setText(email);
                username.setTextColor(getResources().getColor(R.color.white));

                Random r = new Random();

                int valorDado1 = imagenesID[r.nextInt(imagenesID.length)];
                int valorDado2 = imagenesID[r.nextInt(imagenesID.length)];
                int valorDado3 = imagenesID[r.nextInt(imagenesID.length)];
                int valorDado4 = imagenesID[r.nextInt(imagenesID.length)];
                int valorDado5 = imagenesID[r.nextInt(imagenesID.length)];


                dado1.setImageResource(valorDado1);
                dado2.setImageResource(valorDado2);
                dado3.setImageResource(valorDado3);
                dado4.setImageResource(valorDado4);
                dado5.setImageResource(valorDado5);

            }

        });
    }

    @Override
    public void onStart(){
        super.onStart();
        TextView textUSer = findViewById(R.id.textUser);
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            uid = currentUser.getUid();
            email = currentUser.getEmail();
            boolean verificado = currentUser.isEmailVerified();
            Log.i("firebase email", email);
            String mensaje = "Email: " + email;
            if (verificado){
                this.db.collection("users")
                        .whereEqualTo("uid", uid)
                        .get()
                        .addOnCompleteListener(
                                new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for(QueryDocumentSnapshot document : task.getResult()){
                                                String id = document.getId();
                                                db
                                                        .collection("users")
                                                        .document(id)
                                                        .update("verificado", true);
                                                Log.d("TAG", id + " => " + document.getData());
                                            }
                                        }
                                    }
                                }
                        );

            }else{
                mensaje += "\n y necesita ser verificado.";
                //metodo para enviar un link para verificacion
                currentUser.sendEmailVerification();
            }
            textUSer.setText(mensaje);
        } else {
            Log.i("firebase", "NO hay usuario");

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }
    }

    public void setImg(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        if(email != null)
        {
            intent.putExtra("email", email);
        }
        startActivity(intent);
    }

    public void LogoutOnClick(View v){

        /*mAuth.getCurrentUser().()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "User account deleted.");
                        }
                    }
                });*/
        mAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }

}