package com.example.prueba;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DataActivity extends AppCompatActivity {
    //Datos Usuario

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        ImageView imgAvatar = findViewById(R.id.imageAvatar);
        TextView textNombre = findViewById(R.id.textViewNombre);
        TextView textApellido = findViewById(R.id.textViewApellido);
        uid = currentUser.getUid();
        db.collection("users")
                .whereEqualTo("uid", uid)
                .get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot document : task.getResult()){
                                        User user = document.toObject(User.class);
                                        textNombre.setText("Nombre: " + user.nombre);
                                        textApellido.setText("Apellido: " + user.apellido);
                                        ImageView imageView = findViewById(R.id.img);
                                        GetImg descarga = new GetImg(imgAvatar);
                                        descarga.execute(user.avatar);

                                        Log.d("TAG",  user.uid + " => " + document.getData());
                                    }
                                }else {
                                    Log.w("TAG", "Error getting documents.", task.getException());
                                }
                            }
                        }
                );
    }

    public  void returnJuegoOnClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}
