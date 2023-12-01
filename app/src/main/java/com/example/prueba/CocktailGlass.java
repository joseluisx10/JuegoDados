package com.example.prueba;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CocktailGlass extends AsyncTask<String, Integer, String> {

    public  CocktailGlass(){

    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String response = "";
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            DataApi respuesta = objectMapper.readValue(response, DataApi.class);
            response = respuesta.drinks.get(0).strDrink;
            //List<Character> cocktel = respuesta.results;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("testing api", s);
    }


}