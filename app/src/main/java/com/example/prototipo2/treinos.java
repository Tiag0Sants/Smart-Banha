package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prototipo2.Home;
import com.example.prototipo2.R;
import com.example.prototipo2.ganharpeso;
import com.example.prototipo2.manterpeso;
import com.example.prototipo2.perderpeso;

public class treinos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_treinos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurando o botão de voltar
        ImageView btnVoltar = findViewById(R.id.btnvoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treinos.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        // Configurando o botão para a página de Perder Peso
        ImageView btnPerderPeso = findViewById(R.id.btnPerderPeso);
        btnPerderPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treinos.this, perderpeso.class);
                startActivity(intent);
            }
        });



    }
}
