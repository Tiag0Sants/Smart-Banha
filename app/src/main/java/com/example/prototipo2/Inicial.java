package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button cadastre_se, Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        cadastre_se = (Button) findViewById(R.id.cadastre_se);
        Login = (Button) findViewById(R.id.Login);

        cadastre_se.setOnClickListener(this);
        Login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cadastre_se) {
            //se clicou no botão cadastre-se
            Intent tela = new Intent(this, cadastro.class);
            startActivity(tela);
        }
        if (v.getId() == R.id.Login) {
            //se clicou no botão Login
        }
    }
}