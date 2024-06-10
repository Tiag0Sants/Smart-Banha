package com.example.prototipo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipo2.Util.ConfiguraBd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.SignInMethodQueryResult;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class cadastro extends AppCompatActivity {

    EditText cadastro_nome, cadastro_email, cadastro_senha, confirma_senha;
    Button cadastro_botao_proximo;
    FirebaseAuth mAuth;
    HelperClass usuario;
    private static final String TAG = "CadastroActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();
        inicializar();
    }

    private void inicializar(){
        cadastro_nome = findViewById(R.id.cadastro_nome);
        cadastro_email = findViewById(R.id.cadastro_email);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastro_botao_proximo = findViewById(R.id.cadastro_botao_proximo);
        confirma_senha = findViewById(R.id.confirma_senha);

        cadastro_botao_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Botão cadastrar clicado");
                validarCampos(v);
            }
        });
    }

    private void validarCampos(View v){
        String nome = cadastro_nome.getText().toString();
        String email = cadastro_email.getText().toString();
        String senha = cadastro_senha.getText().toString();
        String confirmaSenha = confirma_senha.getText().toString();

        if (!confirmaSenha.equals(senha)){
            Toast.makeText(this, "As senhas não estão iguais!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "Validando campos");

        if (!nome.isEmpty() && isNomeValido(nome)){
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!senha.isEmpty() && isSenhaForte(senha)){
                    if(!confirmaSenha.isEmpty()){
                        verificarEmailFirebase(email, nome);
                    }else{
                        Toast.makeText(this, "Confirme a senha", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Log.d(TAG, "Campo senha vazio ou fraca");
                    Toast.makeText(this, "A senha deve ter pelo menos 6 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial.", Toast.LENGTH_LONG).show();
                }

            }else{
                Log.d(TAG, "Campo email vazio ou inválido");
                Toast.makeText(this, "Preencha um email válido", Toast.LENGTH_SHORT).show();
            }

        }else {
            Log.d(TAG, "Campo nome vazio ou inválido");
            Toast.makeText(this, "Preencha um nome válido", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNomeValido(String nome) {
        // Nome deve ter pelo menos 2 caracteres e apenas letras e espaços
        return nome.matches("^[\\p{L} .'-]+$");
    }

    private boolean isSenhaForte(String senha) {
        boolean isForte = true;
        if (senha.length() < 6) {
            isForte = false;
        }
        if (!senha.matches(".*[A-Z].*")) {
            isForte = false;
        }
        if (!senha.matches(".*[a-z].*")) {
            isForte = false;
        }
        if (!senha.matches(".*\\d.*")) {
            isForte = false;
        }
        if (!senha.matches(".*[@#$%^&+=!].*")) {
            isForte = false;
        }
        return isForte;
    }

    private void verificarEmailFirebase(String email, String nome) {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                    if (isNewUser) {
                        usuario = new HelperClass();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(cadastro_senha.getText().toString());
                        cadastrarUsuario(nome);
                    } else {
                        Toast.makeText(cadastro.this, "Este email já está em uso!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(cadastro.this, "Erro ao verificar email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cadastrarUsuario(String nome){
        mAuth = ConfiguraBd.Firebaseutenticacao();
        mAuth.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(cadastro.this, "Sucesso ao cadastrar o usuário!", Toast.LENGTH_SHORT).show();

                    // Salva o nome do usuário em SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nomeUsuario", nome);
                    editor.apply();

                    // Navegar para a Activity de dados adicionais
                    Intent intent = new Intent(cadastro.this, cadastro2.class);
                    intent.putExtra("usuario", usuario);
                    startActivity(intent);
                    finish();
                }else{
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um email válido!";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já existe!";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(cadastro.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
