package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Connection;
import java.util.List;

public class MainActivityConection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conection);

        TextView[] titulo = new TextView[10];
        ImageView[] foto = new ImageView[10];
        //Async Task
        LinearLayout linearLayout = findViewById(R.id.body);
        for (int i = 0; i < titulo.length; i++) {
            titulo[i] = new TextView(linearLayout.getContext());
            linearLayout.addView(titulo[i]);
        }

        for (int i = 0; i < foto.length; i++) {
            foto[i] = new ImageView(linearLayout.getContext());
            linearLayout.addView(foto[i]);
        }
        checkConnection(titulo, foto);
        Log.i("cantidad", ": "+ titulo.length);
    }

    public void seguirJugandoOnClick(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void checkConnection(TextView[] titulo, ImageView[] foto){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){

            CocktailGlass getCocktail = new CocktailGlass(titulo, foto);
            getCocktail.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Cocktail_glass");

        }else{
            //Mensaje sin internet
            Log.i("TAG", "Sin internet");
        }
    }
}