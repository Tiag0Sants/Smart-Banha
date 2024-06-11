package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public class Home extends AppCompatActivity implements View.OnClickListener {
        ImageView imagem_perfil;
        Button atendimento;
        Button desempenho; // Botão para desempenho
        Button platinhaTreino; // Botão para planilhas de treino
        Button refeicoes;
        HelperClass Help;
        double tmb;
        double imc;

        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            Help = new HelperClass();

            // Obtém os valores de TMB e IMC do SharedPreferences e atribui às variáveis de instância
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            tmb = sharedPreferences.getFloat("TMB_RESULTADO", 0);
            imc = sharedPreferences.getFloat("IMC_RESULTADO", 0);

            // Atualiza os TextViews com os valores de TMB e IMC
            TextView textViewTMB = findViewById(R.id.textViewTMB);
            if (textViewTMB != null) {
                textViewTMB.setText(String.format("Sua TMB é: %.2f", tmb));
            }

            TextView textViewIMC = findViewById(R.id.imc_resultado);
            if (textViewIMC != null) {
                textViewIMC.setText(String.format("Seu IMC é: %.2f", imc));
            }

            imagem_perfil = findViewById(R.id.imagem_perfil);
            imagem_perfil.setOnClickListener(this);

            atendimento = findViewById(R.id.atendimento);
            atendimento.setOnClickListener(this);

            desempenho = findViewById(R.id.Desempenho); // Botão para desempenho
            desempenho.setOnClickListener(this); // Configurar o OnClickListener

            platinhaTreino = findViewById(R.id.platinha_treino); // Botão para planilhas de treino
            platinhaTreino.setOnClickListener(this); // Configurar o OnClickListener

            refeicoes = findViewById(R.id.refeicoes);
            refeicoes.setOnClickListener(this);
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

            if (v.getId() == R.id.Desempenho){
                Intent intent = new Intent(Home.this, MainActivity.class); intent.putExtra("TMB_RESULTADO", tmb); // Passa o valor do TMB
                intent.putExtra("IMC_RESULTADO", imc); // Passa o valor do IMC
                startActivity(intent);

            }

            if(v.getId() == R.id.refeicoes){
                Intent intent = new Intent(Home.this, refeicoes.class);
                startActivity(intent);
                finish();
            }


            if (v.getId() == R.id.platinha_treino) { // Se clicou no botão planilhas de treino
                Intent tela = new Intent(this, treinos.class); // Direcionar para a página de treinos
                startActivity(tela);
            }
        }
    }

