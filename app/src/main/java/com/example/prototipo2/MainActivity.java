package com.example.prototipo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTMB, textViewIMC, textViewNomeUsuario;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTMB = findViewById(R.id.textViewTMB);
        textViewIMC = findViewById(R.id.textViewIMC);
        textViewNomeUsuario = findViewById(R.id.textViewNomeUsuario);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Convert email to a valid Firebase Database key
            String emailKey = currentUser.getEmail().replace(".", "_");

            // Reference to the user's data
            mDatabase = FirebaseDatabase.getInstance().getReference("users").child(emailKey);

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    HelperClass user = dataSnapshot.getValue(HelperClass.class);
                    if (user != null) {
                        double tmb = user.getTmb(); // Supondo que o valor de TMB esteja no objeto HelperClass
                        double imc = user.getImc(); // Supondo que o valor de IMC esteja no objeto HelperClass
                        String nomeUsuario = user.getNome();

                        textViewTMB.setText("Sua TMB é: " + String.format("%.2f", tmb));
                        textViewIMC.setText("Seu IMC é: " + String.format("%.2f", imc));
                        textViewNomeUsuario.setText(nomeUsuario);
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao recuperar dados do usuário", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Erro ao acessar o banco de dados: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Usuário não autenticado", Toast.LENGTH_SHORT).show();
        }
    }
}