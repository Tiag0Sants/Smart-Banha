package com.example.prototipo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView perfilNome, perfilIdade, perfilAltura, perfilNivelAtividade, perfilObjetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializa as views
        btnvoltar = findViewById(R.id.btnvoltar);
        perfilNome = findViewById(R.id.perfil_nome);
        perfilIdade = findViewById(R.id.perfil_titulo_idade);
        perfilAltura = findViewById(R.id.perfil_titulo_altura);
        perfilNivelAtividade = findViewById(R.id.perfil_titulo_atv);
        perfilObjetivo = findViewById(R.id.perfil_titulo_objetivo);

        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button

        // Recupera os dados de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString("nomeUsuario", "Usuário");
        String idadeUsuario = sharedPreferences.getString("idadeUsuario", "N/A");
        String alturaUsuario = sharedPreferences.getString("alturaUsuario", "N/A");
        String nivelAtividadeUsuario = sharedPreferences.getString("nivelAtividadeUsuario", "N/A");
        String objetivoUsuario = sharedPreferences.getString("objetivoUsuario", "N/A");

        // Define os valores nas TextViews
        perfilNome.setText(nomeUsuario);
        perfilIdade.setText(idadeUsuario);
        perfilAltura.setText(alturaUsuario);
        perfilNivelAtividade.setText(nivelAtividadeUsuario);
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
    }
}
