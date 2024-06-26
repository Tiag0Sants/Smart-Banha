package com.example.prototipo2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class treinos extends AppCompatActivity implements View.OnClickListener {

    Button perder, ganhar, manter;
    ImageView btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_treinos);


        perder = findViewById(R.id.btnPerderPeso);
        perder.setOnClickListener(this);

        ganhar = findViewById(R.id.btnGanharPeso);
        ganhar.setOnClickListener(this);

        manter = findViewById(R.id.btnManterPeso);
        manter.setOnClickListener(this);


        // Configurando o botão de voltar
        btnVoltar = findViewById(R.id.btnvoltar);
        btnVoltar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.btnPerderPeso){
            Intent intent = new Intent(this, perderpeso.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.btnGanharPeso){
            Intent intent = new Intent(this, perderpeso.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.btnManterPeso){
            Intent intent = new Intent(this, perderpeso.class);
            startActivity(intent);
            finish();
        }
    }
}
