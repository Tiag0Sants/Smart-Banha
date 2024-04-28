package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    Button atendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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


    }
}

