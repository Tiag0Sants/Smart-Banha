package com.example.prototipo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import android.widget.Button;
import android.widget.EditText;

public class FormLogin extends AppCompatActivity {

    EditText login_email, login_senha;
    Button botao_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        login_email = findViewById(R.id.login_email);
        login_senha = findViewById(R.id.login_senha);
        botao_login = findViewById(R.id.botao_login);

        botao_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                } else {
                    checkUser();
                }
            }
        });

    }

    public Boolean validateUsername() {
        String val = login_email.getText().toString();
        if (val.isEmpty()) {
            login_email.setError("Email não pode estar vazio!");
            return false;
        } else {
            login_email.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = login_senha.getText().toString();
        if (val.isEmpty()) {
            login_senha.setError("A senha não pode estar vazia!");
            return false;
        } else {
            login_senha.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String email = login_email.getText().toString().trim();
        String senha = login_senha.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(email);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    login_email.setError(null);
                    String passwordFromDB = snapshot.child(email).child("senha").getValue(String.class);
                    if (passwordFromDB.equals(senha)) {
                        login_email.setError(null);
                        String nameFromDB = snapshot.child(email).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(email).child("email").getValue(String.class);
                        Intent intent = new Intent(FormLogin.this, perfil.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        login_senha.setError("Senha inexistente");
                        login_senha.requestFocus();
                    }
                } else {
                    login_email.setError("Email inexistente!");
                    login_email.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}