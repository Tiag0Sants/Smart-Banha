package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastro extends AppCompatActivity implements View.OnClickListener {

    EditText cadastro_nome, cadastro_email, cadastro_senha;
    Button cadastrar;
    FirebaseDatabase database;
    DatabaseReference reference;
    ImageView btnvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastro_nome = findViewById(R.id.cadastro_nome);
        cadastro_email = findViewById(R.id.cadastro_email);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastrar = findViewById(R.id.cadastro_botao_proximo);
        btnvoltar = findViewById(R.id.btnvoltar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String nome = cadastro_nome.getText().toString();
                String email = cadastro_email.getText().toString();
                String senha = cadastro_senha.getText().toString();

                // Replacing . with , to avoid issues with Firebase path
                String sanitizedEmail = email.replace(".", ",");

                HelperClass helperClass = new HelperClass(nome, sanitizedEmail, senha);
                reference.child(sanitizedEmail).setValue(helperClass);

                Toast.makeText(cadastro.this, "Seu cadastro deu certo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cadastro.this, FormLogin.class);
                startActivity(intent);
            }
        });

        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, Inicial.class);
            startActivity(tela);
        }
    }
}