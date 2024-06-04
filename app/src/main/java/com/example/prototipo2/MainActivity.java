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
        textViewTMB.setText("Sua TMB é: " + String.format("%.2f", tmb));

        // Atualiza o TextView com o valor do IMC
        TextView textViewIMC = findViewById(R.id.imc_resultado);
        textViewIMC.setText("Seu IMC é: " + String.format("%.2f", imc));

        // Configura o botão de voltar para navegar para a HomeActivity
        ImageView btnVoltar = findViewById(R.id.btnvoltar);
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
