package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recebe os valores da TMB e IMC passados pela Intent
        double tmb = getIntent().getDoubleExtra("TMB_RESULTADO", 0.0);
        double imc = getIntent().getDoubleExtra("IMC_RESULTADO", 0.0);

        // Atualiza o TextView com o valor da TMB
        TextView textViewTMB = findViewById(R.id.textViewTMB);
        if (textViewTMB != null) {
            textViewTMB.setText(String.format("Sua TMB é: %.2f", tmb));
        }

        // Atualiza o TextView com o valor do IMC
        TextView textViewIMC = findViewById(R.id.imc_resultado);
        if (textViewIMC != null) {
            textViewIMC.setText(String.format("Seu IMC é: %.2f", imc));
        }

        // Recupera o nome do usuário da Intent
        String nomeUsuario = getIntent().getStringExtra("nomeUsuario");

        // Atualiza o TextView com o nome do usuário
        TextView textViewNomeUsuario = findViewById(R.id.textViewNomeUsuario);
        if (textViewNomeUsuario != null && nomeUsuario != null) {
            textViewNomeUsuario.setText(nomeUsuario);
        }

        // Configura o botão de voltar para navegar para a HomeActivity
        ImageView btnVoltar = findViewById(R.id.btnvoltar);
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
