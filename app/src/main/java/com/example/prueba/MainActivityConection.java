package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.Connection;

public class MainActivityConection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conection);
        checkConnection();
    }

    public void seguirJugandoOnClick(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            //Async Task
            CocktailGlass getCocktail = new CocktailGlass();
            getCocktail.execute("https://www.thecocktaildb.com/api/json/v1/1/filter.php?g=Cocktail_glass");

        }else{
            //Mensaje sin internet
            Log.i("TAG", "Sin internet");
        }
    }
}