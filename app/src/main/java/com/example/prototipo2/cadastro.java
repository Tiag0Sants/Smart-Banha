package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class cadastro extends AppCompatActivity {

    EditText cadastro_nome, cadastro_email, cadastro_senha;
    Button cadastro_botao_proximo;
    FirebaseAuth mAuth;
    //FirebaseDatabase database;
    //DatabaseReference reference;
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

        Log.d(TAG, "Validando campos");

        if (!nome.isEmpty()){
            if (!email.isEmpty()){
                if (!senha.isEmpty()){
                    HelperClass usuario = new HelperClass();
                    usuario.setName(nome);
                    usuario.setEmail(email);
                    usuario.setPassword(senha);

                    //cadastrar
                    cadastrarUsuario();

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
        Log.d(TAG, "Cadastrar usuário chamado");
        // Implemente o código para cadastrar o usuário
    }
}
