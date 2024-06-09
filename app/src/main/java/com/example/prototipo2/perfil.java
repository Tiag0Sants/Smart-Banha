package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<<<< Temporary merge branch 1
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
=========
import android.widget.Toast;

public class perfil extends AppCompatActivity implements View.OnClickListener {
    ImageView btnvoltar;
    TextView nome;
>>>>>>>>> Temporary merge branch 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnvoltar = findViewById(R.id.btnvoltar);
<<<<<<<<< Temporary merge branch 1
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
=========
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
>>>>>>>>> Temporary merge branch 2
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
