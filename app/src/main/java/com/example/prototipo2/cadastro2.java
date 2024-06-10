package com.example.prototipo2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class cadastro2 extends AppCompatActivity {

    EditText idade, peso, altura;
    RadioGroup generoGroup, objetivoGroup, nivelAtividadeGroup;
    Button finalizarCadastro;
    HelperClass usuario;

    private static final String TAG = "Cadastro2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        // Inicializa os componentes da interface
        inicializar();

        // Recebe os dados do usuário da Intent anterior
        Intent intent = getIntent();
        usuario = (HelperClass) intent.getSerializableExtra("usuario");

        // Verifica se os dados do usuário foram recebidos corretamente
        if (usuario == null) {
            Toast.makeText(this, "Erro ao receber dados do usuário", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Usuário recebido é nulo");
            finish(); // Encerra a Activity se os dados do usuário não foram recebidos corretamente
            return;
        }

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

        // Verificações para garantir que os componentes não são nulos
        if (idade == null) Log.e(TAG, "Erro ao inicializar 'idade'");
        if (peso == null) Log.e(TAG, "Erro ao inicializar 'peso'");
        if (altura == null) Log.e(TAG, "Erro ao inicializar 'altura'");
        if (generoGroup == null) Log.e(TAG, "Erro ao inicializar 'generoGroup'");
        if (objetivoGroup == null) Log.e(TAG, "Erro ao inicializar 'objetivoGroup'");
        if (nivelAtividadeGroup == null) Log.e(TAG, "Erro ao inicializar 'nivelAtividadeGroup'");
        if (finalizarCadastro == null) Log.e(TAG, "Erro ao inicializar 'finalizarCadastro'");
    }

    @SuppressLint("NonConstantResourceId")
    private void calcularETransferirDados() {
        String strIdade = idade.getText().toString();
        String strPeso = peso.getText().toString();
        String strAltura = altura.getText().toString();

        if (!strIdade.isEmpty() && !strPeso.isEmpty() && !strAltura.isEmpty()) {
            try {
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
                editor.putInt("idadeUsuario", idade);
                editor.putFloat("pesoUsuario", peso);
                editor.putFloat("alturaUsuario", altura);
                editor.putString("generoUsuario", genero);
                editor.putString("objetivoUsuario", objetivo);
                editor.apply();

                // Chamar a função para calcular TMB e IMC e navegar para a próxima Activity
                calcularETransferirTMB();
            } catch (Exception e) {
                Log.e(TAG, "Erro ao calcular e transferir dados: ", e);
                Toast.makeText(this, "Erro ao processar os dados", Toast.LENGTH_SHORT).show();
            }
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
        try {
            // Recupera os dados do usuário
            int idade = usuario.getAge();
            float peso = usuario.getWeight();
            float altura = usuario.getHeight();
            String genero = usuario.getGender();

            // Calcular TMB (Taxa Metabólica Basal)
            double tmb;
            if (genero.equalsIgnoreCase("Masculino")) {
                tmb = 88.36 + (13.4 * peso) + (4.8 * altura * 100) - (5.7 * idade);
            } else {
                tmb = 447.6 + (9.2 * peso) + (3.1 * altura * 100) - (4.3 * idade);
            }

            // Calcular IMC (Índice de Massa Corporal)
            float imc = peso / (altura * altura);

            // Armazenar os cálculos no objets HelperClass
            usuario.setTmb(tmb);
            usuario.setImc(imc);

            // Salvar os valores de TMB e IMC no SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("TMB_RESULTADO", (float) tmb); // Salva o valor de TMB
            editor.putFloat("IMC_RESULTADO", imc); // Salva o valor de IMC
            editor.apply(); // Aplica as alterações

            // Passar os dados para a tela Home
            Intent intent = new Intent(cadastro2.this, Home.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Erro ao calcular TMB e IMC: ", e);
            Toast.makeText(this, "Erro ao calcular TMB e IMC", Toast.LENGTH_SHORT).show();
        }
    }

}
