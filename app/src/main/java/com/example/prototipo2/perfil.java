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
    Button sair, editar;

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
        editar = findViewById(R.id.perfil_botao_editar);

        // Definindo os listeners de clique
        sair.setOnClickListener(this);
        editar.setOnClickListener(this);
        btnvoltar.setOnClickListener(this);

        // Atualiza os dados do perfil
        atualizarDadosPerfil();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
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
            Intent tela = new Intent(this, Edicao.class);
            startActivity(tela);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza os dados do perfil ao retornar à página
        atualizarDadosPerfil();
    }

    private void atualizarDadosPerfil() {
        // Recupera os dados de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("perfil_preferences", MODE_PRIVATE);
        String nomeUsuario = sharedPreferences.getString("nomeUsuario", "Usuário");
        int idadeUsuario = sharedPreferences.getInt("idadeUsuario", 0);
        float alturaUsuario = sharedPreferences.getFloat("alturaUsuario", 0);
        String[] objetivos = getResources().getStringArray(R.array.opcoes_objetivo);
        int objetivoIndex = sharedPreferences.getInt("objetivoUsuario", 0);
        String objetivoUsuario = objetivos[objetivoIndex];

        // Define os valores nas TextViews
        perfilNome.setText(nomeUsuario);
        perfilIdade.setText(String.valueOf(idadeUsuario));
        perfilAltura.setText(String.valueOf(alturaUsuario));
        perfilObjetivo.setText(objetivoUsuario);
    }
}
