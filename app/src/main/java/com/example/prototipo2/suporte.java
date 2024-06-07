package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo2.MainActivity;
import com.example.prototipo2.R;
import com.example.prototipo2.perfil;

public class suporte extends AppCompatActivity implements View.OnClickListener {

    ImageView btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        btnVoltar = findViewById(R.id.btnvoltar);
        btnVoltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // se clicou no botão voltar
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }
}