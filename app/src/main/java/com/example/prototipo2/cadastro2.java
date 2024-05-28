package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastro2 extends AppCompatActivity {

    EditText idade, peso, altura;
    RadioGroup generoGroup, objetivoGroup;
    Button finalizarCadastro;
    HelperClass usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        inicializar();

        Intent intent = getIntent();
        usuario = (HelperClass) intent.getSerializableExtra("usuario");

        finalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDados(v);
            }
        });
    }

    private void inicializar() {
        idade = findViewById(R.id.valor_altura);
        peso = findViewById(R.id.valor_peso);
        altura = findViewById(R.id.valor_altura);
        generoGroup = findViewById(R.id.genero_grupo);
        objetivoGroup = findViewById(R.id.obj_grupo);
        finalizarCadastro = findViewById(R.id.cadastro_botao_finalizar);
    }

    private void salvarDados(View v) {
        String strIdade = idade.getText().toString();
        String strPeso = peso.getText().toString();
        String strAltura = altura.getText().toString();

        if (!strIdade.isEmpty() && !strPeso.isEmpty() && !strAltura.isEmpty()) {
            int idade = Integer.parseInt(strIdade);
            float peso = Float.parseFloat(strPeso);
            float altura = Float.parseFloat(strAltura);

            usuario.setAge(idade);
            usuario.setWeight(peso);
            usuario.setHeight(altura);

            int selectedGeneroId = generoGroup.getCheckedRadioButtonId();
            RadioButton selectedGenero = findViewById(selectedGeneroId);
            usuario.setGender(selectedGenero.getText().toString());

            int selectedObjetivoId = objetivoGroup.getCheckedRadioButtonId();
            RadioButton selectedObjetivo = findViewById(selectedObjetivoId);
            usuario.setGoal(selectedObjetivo.getText().toString());

            // Salvar no Firebase
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(usuario.getEmail().replace(".", "_")).setValue(usuario);

            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            finish(); // Fechar a Activity após o cadastro
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}
