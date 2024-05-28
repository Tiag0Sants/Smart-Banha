package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity implements View.OnClickListener {
    ImageView imagem_perfil;
    Button atendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imagem_perfil = findViewById(R.id.imagem_perfil);  // Use o ID correto
        imagem_perfil.setOnClickListener(this);

        atendimento = findViewById(R.id.atendimento);
        atendimento.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.atendimento) {
            // se clicou no botão atendimento ao cliente
            Intent tela = new Intent(this, suporte.class);
            startActivity(tela);
        }

        if (v.getId() == R.id.imagem_perfil) {
            // se clicou na imagem de perfil
            Intent tela = new Intent(this, perfil.class);
            startActivity(tela);
        }
    }
}