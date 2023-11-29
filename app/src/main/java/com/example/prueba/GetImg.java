package com.example.prueba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GetImg extends AsyncTask<String, Integer, Bitmap> {

    Bitmap imagenDescargada = null;
    ImageView imageView;

    public GetImg(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Log.i("testing", "Downloading");
        try {
            URL descarga = new URL(strings[0]);
            InputStream streamImagen = (InputStream) descarga.getContent();
            imagenDescargada = BitmapFactory.decodeStream(streamImagen);
        }catch (IOException e){
            e.printStackTrace();
        }
        return imagenDescargada;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap == null) return;
        this.imageView.setImageBitmap(bitmap);
    }
}
