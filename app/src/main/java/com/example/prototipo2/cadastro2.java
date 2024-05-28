package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class cadastro2 extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        btnvoltar = findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, cadastro.class);
            startActivity(tela);
        }
    }
}