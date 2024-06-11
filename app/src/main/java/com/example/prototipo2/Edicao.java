package com.example.prototipo2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipo2.R;

public class Edicao extends AppCompatActivity {

    // Nome do arquivo de preferências
    private static final String PREF_NAME = "perfil_preferences";

    // Chaves para as preferências
    private static final String KEY_OBJETIVO = "objetivo";

    private SharedPreferences sharedPreferences;
    Spinner opcoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edicao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialize o SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Encontre o spinner no layout
        opcoes = findViewById(R.id.spinner_objetivo);

        // Defina os dados para o spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_objetivo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcoes.setAdapter(adapter);

        // Recupere o valor salvo do SharedPreferences e defina-o como a seleção do Spinner
        int objetivoIndex = sharedPreferences.getInt(KEY_OBJETIVO, 0); // 0 é o índice padrão se não houver valor salvo
        opcoes.setSelection(objetivoIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Salve a seleção atual do usuário no SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int objetivoIndex = opcoes.getSelectedItemPosition();
        editor.putInt(KEY_OBJETIVO, objetivoIndex);
        editor.apply();

        // Volte para a página do perfil
        finish();
    }
}
