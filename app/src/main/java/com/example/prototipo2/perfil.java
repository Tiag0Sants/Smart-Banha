package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView perfil_nome, perfil_altura, perfil_idade, perfil_atividade, perfil_objetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Instance();

        btnvoltar = findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
        }
    }

    public void Instance(){
        perfil_nome = findViewById(R.id.perfil_nome);
        perfil_altura = findViewById(R.id.perfil_altura);
        perfil_idade = findViewById(R.id.perfil_idade);
        perfil_atividade = findViewById(R.id.perfil_atividade);
        perfil_objetivo = findViewById(R.id.perfil_objetivo);


    }

    public void Dados(){

    }
}