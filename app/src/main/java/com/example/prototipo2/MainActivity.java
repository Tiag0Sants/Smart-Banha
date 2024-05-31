package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recebe o valor da TMB passado pela Intent
        double tmb = getIntent().getDoubleExtra("TMB_RESULTADO", 0.0);

        // Atualiza o TextView com o valor da TMB
        TextView textViewTMB = findViewById(R.id.textViewTMB);
        textViewTMB.setText("Sua TMB é: " + String.format("%.2f", tmb));
    }

}