package com.example.prototipo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class health_tips extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        TextView textViewHealthTips = findViewById(R.id.textViewHealthTips);
        ImageView btnVoltar = findViewById(R.id.btnvoltar);

        // Exemplo de dicas de saúde
        String healthTips = "Dicas de Saúde:\n\n" +
                "1. Mantenha uma dieta equilibrada e saudável.\n" +
                "2. Beba muita água ao longo do dia.\n" +
                "3. Pratique exercícios físicos regularmente.\n" +
                "4. Durma pelo menos 7-8 horas por noite.\n" +
                "5. Evite o consumo excessivo de álcool e tabaco.\n" +
                "6. Realize check-ups médicos regularmente.\n" +
                "7. Gerencie o estresse através de técnicas de relaxamento como meditação ou yoga.";

        textViewHealthTips.setText(healthTips);

        // Configurar o botão de voltar para retornar à MainActivity
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar a MainActivity
                Intent intent = new Intent(health_tips.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: finalizar a atividade atual
            }
        });
    }
}
