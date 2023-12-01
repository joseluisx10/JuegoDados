package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView label = findViewById(R.id.txtemail);
        Intent result = this.getIntent();
        if(result.hasExtra("email")){
            String email = this.getIntent().getStringExtra("email");
            Log.i("TAG", "recibimos: " + email);
            label.setText(email);
        }
    }

    public  void returnJuegoOnClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void downloadOnClick(View v)
    {
        ImageView imageView = findViewById(R.id.img);
        GetImg descarga1 = new GetImg(imageView);
        descarga1.execute("https://image.isu.pub/111003142337-1422650687f846a499552a50a8ce34fb/jpg/page_1_thumb_large.jpg");

    }

    public void cocktailApi(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivityConection.class);
        startActivity(intent);
    }




}