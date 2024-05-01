package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FormLogin extends AppCompatActivity implements View.OnClickListener {

    Button botao_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);


        botao_login = (Button) findViewById(R.id.botao_login);
        botao_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            if (v.getId() == R.id.botao_login) {
                //se clicou no botão login
                Intent tela = new Intent(this, Home.class);
                startActivity(tela);
            }


    }
}
