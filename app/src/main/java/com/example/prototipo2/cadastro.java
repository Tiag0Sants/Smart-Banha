package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class cadastro extends AppCompatActivity {

    EditText nome, email, senha;
    Button proximo;
    HelperClass usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);
        proximo = findViewById(R.id.cadastro_botao_proximo);

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNome = nome.getText().toString();
                String strEmail = email.getText().toString();
                String strSenha = senha.getText().toString();

                if (!strNome.isEmpty() && !strEmail.isEmpty() && !strSenha.isEmpty()) {
                    usuario = new HelperClass(strNome, strEmail, strSenha);

                    Intent intent = new Intent(cadastro.this, cadastro2.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                } else {
                    Toast.makeText(cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
