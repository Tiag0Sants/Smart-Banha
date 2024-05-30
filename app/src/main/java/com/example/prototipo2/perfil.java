package com.example.prototipo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfil extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnvoltar;
    private TextView perfilNome, perfilIdade, perfilAltura, perfilAtv, perfilObjetivo;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnvoltar = findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);

        perfilNome = findViewById(R.id.perfil_nome);
        perfilIdade = findViewById(R.id.perfil_idade);
        perfilAltura = findViewById(R.id.perfil_altura);
        perfilAtv = findViewById(R.id.perfil_atv);
        perfilObjetivo = findViewById(R.id.perfil_objetivo);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    HelperClass user = dataSnapshot.getValue(HelperClass.class);
                    if (user != null) {
                        perfilNome.setText(user.getNome());
                        perfilIdade.setText(String.valueOf(user.getAge()));  // Atualizado para getAge()
                        perfilAltura.setText(String.valueOf(user.getHeight()));  // Atualizado para getHeight()
                        perfilAtv.setText(user.getNivelDeAtividade());
                        perfilObjetivo.setText(user.getGoal());  // Atualizado para getGoal()
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(perfil.this, "Erro ao carregar dados do usuário", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Usuário não está logado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
        }
    }
}