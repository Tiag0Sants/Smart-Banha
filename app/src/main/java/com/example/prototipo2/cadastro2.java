package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class cadastro2 extends AppCompatActivity {

    EditText nome, idade, peso, altura;
    RadioGroup generoGroup, objetivoGroup, nivelAtividadeGroup;
    Button finalizarCadastro;
    HelperClass usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        // Inicializa os componentes da interface
        inicializar();

        // Recebe os dados do usuário da Intent anterior
        Intent intent = getIntent();
        usuario = (HelperClass) intent.getSerializableExtra("usuario");

        // Configura o listener para o botão de finalizar cadastro
        finalizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularETransferirDados(); // Chama a função para calcular e transferir os dados
            }
        });
    }

    // Função para inicializar os componentes da interface
    private void inicializar() {
        idade = findViewById(R.id.valor_idade);
        peso = findViewById(R.id.valor_peso);
        altura = findViewById(R.id.valor_altura);
        generoGroup = findViewById(R.id.genero_grupo);
        objetivoGroup = findViewById(R.id.obj_grupo);
        nivelAtividadeGroup = findViewById(R.id.nvl);
        finalizarCadastro = findViewById(R.id.cadastro_botao_finalizar);
    }

    @SuppressLint("NonConstantResourceId")
    private void calcularETransferirDados() {
        String strNome = nome.getText().toString();
        String strIdade = idade.getText().toString();
        String strPeso = peso.getText().toString();
        String strAltura = altura.getText().toString();

        if (!strNome.isEmpty() && !strIdade.isEmpty() && !strPeso.isEmpty() && !strAltura.isEmpty()) {
            // Recuperar os valores dos campos
            int idade = Integer.parseInt(strIdade);
            float peso = Float.parseFloat(strPeso);
            float altura = Float.parseFloat(strAltura);
            int selectedGeneroId = generoGroup.getCheckedRadioButtonId();
            RadioButton selectedGenero = findViewById(selectedGeneroId);
            String genero = selectedGenero.getText().toString();
            int selectedNivelId = nivelAtividadeGroup.getCheckedRadioButtonId();
            double fatorAtividade = obterFatorAtividade(selectedNivelId);
            String objetivo = obterObjetivo();

            // Armazenar os dados no objeto HelperClass
            usuario.setAge(idade);
            usuario.setWeight(peso);
            usuario.setHeight(altura);
            usuario.setGender(genero);
            usuario.setGoal(objetivo);

            // Armazenar os dados no SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nomeUsuario", strNome);
            editor.putInt("idadeUsuario", idade);
            editor.putFloat("pesoUsuario", peso);
            editor.putFloat("alturaUsuario", altura);
            editor.putString("generoUsuario", genero);
            editor.putString("objetivoUsuario", objetivo);
            editor.apply();

            // Chamar a função para calcular TMB e IMC e navegar para a próxima Activity
            calcularETransferirTMB();
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    // Função para obter o fator de atividade com base no ID do RadioButton selecionado
    private double obterFatorAtividade(int selectedId) {
        double fatorAtividade = 1.0;
        if (selectedId == R.id.radio_baixo) {
            fatorAtividade = 1.2;
        } else if (selectedId == R.id.radio_moderado) {
            fatorAtividade = 1.55;
        } else if (selectedId == R.id.radio_alto) {
            fatorAtividade = 1.725;
        }
        return fatorAtividade;
    }


    // Função para obter o objetivo com base no RadioButton selecionado
    private String obterObjetivo() {
        String objetivo = "";
        int selectedObjetivoId = objetivoGroup.getCheckedRadioButtonId();
        if (selectedObjetivoId != -1) {
            RadioButton selectedObjetivo = findViewById(selectedObjetivoId);
            objetivo = selectedObjetivo.getText().toString();
        }
        return objetivo;
    }

    // Função para calcular TMB e IMC e navegar para a próxima Activity
    private void calcularETransferirTMB() {
        // Implemente o cálculo da TMB e IMC e a navegação para a próxima Activity aqui
    }
}
