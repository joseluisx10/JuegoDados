package com.example.prueba;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CocktailGlass extends AsyncTask<String, Integer, List<Drinks>> {
    public TextView[] tit;
    public  ImageView[] img;

    public  CocktailGlass(TextView [] text, ImageView [] img){
        this.tit = text;
        this.img = img;

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
    protected List<Drinks> doInBackground(String... strings) {
        String url = strings[0];
        List<Drinks> cocktels = null;
        String response = "";
        try {
            response = run(url);
            ObjectMapper objectMapper = new ObjectMapper();
            DataApi res = objectMapper.readValue(response, DataApi.class);

            //response = res.drinks.get(0).strDrink;
            cocktels = res.drinks;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cocktels;
    }

    /*@Override
    protected void onPostExecute(List<Drinks> s) {
        super.onPostExecute(s);
        //Log.i("testing api", s);
        for (int i = 0; i < this.tit.length; i++) {
            tit[i].setText(s.get(i).strDrink);
            Log.i("testing api", s.get(i).strDrink);

        }
    }*/

    @Override
    protected void onPostExecute(List<Drinks> s) {
        super.onPostExecute(s);
        if (tit != null && s != null && !s.isEmpty()) {
            for (int i = 0; i < Math.min(s.size(), tit.length); i++) {
                tit[i].setText(s.get(i).strDrink);
                GetImg imagen = new GetImg(img[i]);
                imagen.execute(s.get(i).strDrinkThumb);
                Log.i("testing api", s.get(i).strDrink);
            }
        } else {
            // Maneja el caso en el que el array tit sea nulo o la lista sea nula o vacía
            Log.e("testing api", "El array tit es nulo o la lista de cocktails es nula o vacía");
        }
    }


}