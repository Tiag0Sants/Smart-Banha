package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView perfil_nome, perfil_idade, perfil_altura, perfil_atividade, perfil_objetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnvoltar = findViewById(R.id.btnvoltar);
        btnvoltar.setOnClickListener(this);

        // Inicializa as TextViews para exibir as informações do perfil do usuário
        perfil_nome = findViewById(R.id.perfil_nome);
        perfil_idade = findViewById(R.id.perfil_titulo_idade);
        perfil_altura = findViewById(R.id.perfil_titulo_altura);
        perfil_atividade = findViewById(R.id.perfil_titulo_atv);
        perfil_objetivo = findViewById(R.id.perfil_titulo_objetivo);

        // Obtém a referência do usuário atualmente autenticado no Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Obtém o ID do usuário atual
            String userId = user.getUid();

            // Obtém a referência do nó do usuário no Firebase Realtime Database
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

            // Adiciona um ValueEventListener para recuperar os dados do usuário
            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Verifica se os dados existem antes de tentar acessá-los
                    if (dataSnapshot.exists()) {
                        // Obtém os dados do usuário
                        String nome = dataSnapshot.child("nome").getValue(String.class);
                        String idade = dataSnapshot.child("idade").getValue(String.class);
                        String altura = dataSnapshot.child("altura").getValue(String.class);
                        String atividade = dataSnapshot.child("nivelAtividade").getValue(String.class);
                        String objetivo = dataSnapshot.child("objetivo").getValue(String.class);

                        // Atualiza as TextViews com as informações do perfil do usuário
                        perfil_nome.setText(nome);
                        perfil_idade.setText(idade);
                        perfil_altura.setText(altura);
                        perfil_atividade.setText(atividade);
                        perfil_objetivo.setText(objetivo);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Trate os erros, se houver algum
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnvoltar) {
            // If back button is clicked
            Intent tela = new Intent(this, Home.class);
            startActivity(tela);
        }
    }
}
