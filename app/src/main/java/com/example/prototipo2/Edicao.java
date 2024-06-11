package com.example.prototipo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Edicao extends AppCompatActivity implements View.OnClickListener {

    // Nome do arquivo de preferências
    private static final String PREF_NAME = "perfil_preferences";

    // Chaves para as preferências
    private static final String KEY_NOME = "nomeUsuario";
    private static final String KEY_IDADE = "idadeUsuario";
    private static final String KEY_ALTURA = "alturaUsuario";
    private static final String KEY_OBJETIVO = "objetivoUsuario";

    private SharedPreferences sharedPreferences;
    Spinner opcoes;
    Button salvar;
    EditText editNome, editIdade, editAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        // Inicialize as views
        opcoes = findViewById(R.id.spinner_objetivo);
        editNome = findViewById(R.id.edit_nome);
        editIdade = findViewById(R.id.edit_idade);
        editAltura = findViewById(R.id.edit_altura);
        salvar = findViewById(R.id.perfil_botao_salvar);

        // Inicialize o spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_objetivo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcoes.setAdapter(adapter);

        salvar.setOnClickListener(this);

        // Inicialize o SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Recupere os valores salvos do SharedPreferences e defina-os nas views
        editNome.setText(sharedPreferences.getString(KEY_NOME, ""));
        editIdade.setText(String.valueOf(sharedPreferences.getInt(KEY_IDADE, 0)));
        editAltura.setText(String.valueOf(sharedPreferences.getFloat(KEY_ALTURA, 0.0f)));
        int objetivoIndex = sharedPreferences.getInt(KEY_OBJETIVO, 0);
        opcoes.setSelection(objetivoIndex);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.perfil_botao_salvar) {
            // Salve os dados no SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NOME, editNome.getText().toString());
            editor.putInt(KEY_IDADE, Integer.parseInt(editIdade.getText().toString()));
            editor.putFloat(KEY_ALTURA, Float.parseFloat(editAltura.getText().toString()));
            editor.putInt(KEY_OBJETIVO, opcoes.getSelectedItemPosition());
            editor.apply();

            // Volte para a página do perfil
            Intent intent = new Intent(this, perfil.class);
            startActivity(intent);
            finish();
        }
    }
}
