package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnvoltar = findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);  // Setting the onClickListener for the back button

        nome = findViewById(R.id.perfil_nome);

        // Assume that the email is passed via Intent from the previous activity
        String email = getIntent().getStringExtra("USER_EMAIL");
        if (email != null) {
            loadUserName(email);
        } else {
            Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadUserName(String email) {
        FirestoreHelper firestoreHelper = new FirestoreHelper();
        firestoreHelper.getUserData(email, new FirestoreHelper.FirestoreCallback() {
            @Override
            public void onCallback(String name) {
                nome.setText(name);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(perfil.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
            finish();  // Optionally, finish the current activity
        }
    }
}
