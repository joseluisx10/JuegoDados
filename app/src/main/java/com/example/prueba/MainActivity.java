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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;



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

                username.setText("User name: Matias");
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

        downloadOnClick(dado1, dado2, dado3, dado4, dado5);
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            String email = currentUser.getEmail();
            Log.i("firebase email", email);
        } else {
            Log.i("firebase", "NO hay usuario");

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }
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

    public void downloadOnClick(ImageView dado1, ImageView dado2, ImageView dado3, ImageView dado4, ImageView dado5)
    {

        GetImg descarga1 = new GetImg(dado1);
        GetImg descarga2 = new GetImg(dado2);
        GetImg descarga3 = new GetImg(dado3);
        GetImg descarga4 = new GetImg(dado4);
        GetImg descarga5 = new GetImg(dado5);
        descarga1.execute("https://www.dadovirtual.site/img/dados/cara11.png");
        descarga2.execute("https://p7.hiclipart.com/preview/707/718/498/shake-dice-dice-game-cute-red-dice.jpg");
        descarga3.execute("https://mysuenos.com/wp-content/uploads/dados-numero-3.jpg");
        descarga4.execute("https://www.shutterstock.com/image-illustration/red-dice-number-4-260nw-249476653.jpg");
        descarga5.execute("https://mediateca.educa.madrid.org/imagen.php?id=qvy1u3pbzmwf3tu9&m=0&ext=.jpg");

    }





}