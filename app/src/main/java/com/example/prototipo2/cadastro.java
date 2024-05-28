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

import android.content.Intent;
import android.util.Log;
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

        if (!nome.isEmpty()){
            if (!email.isEmpty()){
                if (!senha.isEmpty()){
                    if(!confirmaSenha.isEmpty()){
                        usuario = new HelperClass();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);

                        // Cadastrar usuário
                        cadastrarUsuario();
                    }else{
                        Toast.makeText(this, "Confirme a senha", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Log.d(TAG, "Campo senha vazio");
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                }

            }else{
                Log.d(TAG, "Campo email vazio");
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }

        }else {
            Log.d(TAG, "Campo nome vazio");
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario(){
        mAuth = ConfiguraBd.Firebaseutenticacao();
        mAuth.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(cadastro.this, "Sucesso ao cadastrar o usuário!", Toast.LENGTH_SHORT).show();
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
