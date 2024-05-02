package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;


public class cadastro extends AppCompatActivity {

    EditText nome, email, senha;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);


    }
}