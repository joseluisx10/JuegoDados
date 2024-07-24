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
        // Iteramos sobre los elementos
        for (int i = 0; i < titulo.length; i++) {
            LinearLayout itemLayout = new LinearLayout(linearLayout.getContext());
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            titulo[i] = new TextView(itemLayout.getContext());
            foto[i] = new ImageView(itemLayout.getContext());

            // Configuramos el TextView
            titulo[i].setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1 // Peso para ocupar el máximo espacio disponible
            ));
            titulo[i].setTextSize(18);
            titulo[i].setPadding(16, 16, 16, 16);

            // Configuramos el ImageView
            foto[i].setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            foto[i].setPadding(16, 16, 16, 16);

            itemLayout.addView(titulo[i]);
            itemLayout.addView(foto[i]);

            // Añadimos el LinearLayout horizontal al LinearLayout principal
            linearLayout.addView(itemLayout);
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