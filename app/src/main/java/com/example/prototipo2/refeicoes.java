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

public class refeicoes extends AppCompatActivity implements View.OnClickListener {

    Button perder, ganhar, manter;
    ImageView btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_refeicoes);


        perder = findViewById(R.id.RefeicaoPerderPeso);
        perder.setOnClickListener(this);

        ganhar = findViewById(R.id.RefeicaoGanharPeso);
        ganhar.setOnClickListener(this);

        manter = findViewById(R.id.RefeicaoManterPeso);
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

        if (v.getId() == R.id.RefeicaoPerderPeso){
            Intent intent = new Intent(this, RefeicaoPerderPeso.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.RefeicaoGanharPeso){
            Intent intent = new Intent(this, RefeicaoGanharPeso.class);
            startActivity(intent);
            finish();
        }

        if (v.getId() == R.id.RefeicaoManterPeso){
            Intent intent = new Intent(this, RefeicaoManterPeso.class);
            startActivity(intent);
            finish();
        }
    }
}
