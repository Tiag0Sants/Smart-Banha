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

    EditText cadastro_nome, cadastro_email, cadastro_senha;
    Button cadastrar;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastro_nome = findViewById(R.id.cadastro_nome);
        cadastro_email = findViewById(R.id.cadastro_email);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastrar = findViewById(R.id.cadastro_botao_proximo);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String nome = cadastro_nome.getText().toString();
                String email = cadastro_email.getText().toString();
                String senha = cadastro_senha.getText().toString();

                HelperClass helperClass = new HelperClass(nome, email, senha);
                reference.child(email).setValue(helperClass);

                Toast.makeText(cadastro.this, "Seu cadastro deu certo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cadastro.this, FormLogin.class);
                startActivity(intent);
            }
        });

    }
}