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

    }
}