package com.example.prototipo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView perfilNome, perfilIdade, perfilAltura, perfilObjetivo;
    Button sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializa as views
        btnvoltar = findViewById(R.id.btnvoltar);
        perfilNome = findViewById(R.id.perfil_nome);
        perfilIdade = findViewById(R.id.perfil_idade);
        perfilAltura = findViewById(R.id.perfil_altura);
        perfilObjetivo = findViewById(R.id.perfil_obj);
        sair = findViewById(R.id.perfil_botao_sair);
        sair.setOnClickListener(this);

        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button

        // Recupera os dados de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString("nomeUsuario", "Usuário");
        int idadeUsuario = sharedPreferences.getInt("idadeUsuario", 0); // Modificado para recuperar um int
        float alturaUsuario = sharedPreferences.getFloat("alturaUsuario", 0); // Modificado para recuperar um float
        String objetivoUsuario = sharedPreferences.getString("objetivoUsuario", "N/A");

        // Define os valores nas TextViews
        perfilNome.setText(nomeUsuario);
        perfilIdade.setText(String.valueOf(idadeUsuario)); // Convertido para String
        perfilAltura.setText(String.valueOf(alturaUsuario)); // Convertido para String
        perfilObjetivo.setText(objetivoUsuario);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
            finish();
        }

        if (v.getId() == R.id.perfil_botao_sair){
            Intent tela = new Intent(this, Inicial.class);
            startActivity(tela);
            finish();
        }
        if (v.getId() == R.id.perfil_botao_editar){
            Intent tela = new Intent(this, cadastro2.class);
            startActivity(tela);
            finish();
        }

    }
}
