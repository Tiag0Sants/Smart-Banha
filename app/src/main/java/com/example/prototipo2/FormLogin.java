package com.example.prototipo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototipo2.Util.ConfiguraBd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private EditText login_email, login_senha;
    private Button login_botao;
    private FirebaseAuth mAuth;
    private static final String TAG = "FormLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        mAuth = FirebaseAuth.getInstance();
        inicializar();
    }

    private void inicializar() {
        login_email = findViewById(R.id.login_email);
        login_senha = findViewById(R.id.login_senha);
        login_botao = findViewById(R.id.botao_login);

        login_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Botão login clicado");
                validarLogin(v);
            }
        });
    }

    private void validarLogin(View v) {
        String email = login_email.getText().toString();
        String senha = login_senha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()) {
            realizarLogin(email, senha);
        } else {
            Toast.makeText(FormLogin.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void realizarLogin(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FormLogin.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                            // Navegar para a tela principal da aplicação
                            Intent intent = new Intent(FormLogin.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String excecao = "";
                            try {
                                throw task.getException();
                            } catch (Exception e) {
                                excecao = "Erro ao realizar login: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(FormLogin.this, excecao, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
