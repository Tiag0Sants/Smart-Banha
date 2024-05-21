package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;

    Button atendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnvoltar = (ImageView) findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);

        atendimento = (Button) findViewById(R.id.atendimento);
        atendimento.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.atendimento) {
            //se clicou no botão atendimento ao cliente
            Intent tela = new Intent(this, suporte.class);
            startActivity(tela);
        }

        if (v.getId() == R.id.btnvoltar) {
            //se clicou no botão Voltar
            Intent tela = new Intent(this, Inicial.class);
            startActivity(tela);
        }

    }
}

