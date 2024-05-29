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
                calcularETransferirTMB(); // Chama a função para calcular e transferir a TMB
            }
        });
    }

    // Função para inicializar os componentes da interface
    private void inicializar() {
        idade = findViewById(R.id.valor_idade); // Corrigido o ID para valor_idade
        peso = findViewById(R.id.valor_peso);
        altura = findViewById(R.id.valor_altura);
        generoGroup = findViewById(R.id.genero_grupo);
        objetivoGroup = findViewById(R.id.obj_grupo);
        nivelAtividadeGroup = findViewById(R.id.nvl); // Adicionado o RadioGroup para nível de atividade física
        finalizarCadastro = findViewById(R.id.cadastro_botao_finalizar);
    }

    // Função para calcular a TMB e transferir os dados para a próxima Activity
    private void calcularETransferirTMB() {
        // Obtenção dos valores inseridos
        String strIdade = idade.getText().toString();
        String strPeso = peso.getText().toString();
        String strAltura = altura.getText().toString();

        if (!strIdade.isEmpty() && !strPeso.isEmpty() && !strAltura.isEmpty()) {
            int idade = Integer.parseInt(strIdade);
            float peso = Float.parseFloat(strPeso);
            float altura = Float.parseFloat(strAltura);

            // Obtém o gênero selecionado
            int selectedGeneroId = generoGroup.getCheckedRadioButtonId();
            if (selectedGeneroId == -1) {
                Toast.makeText(this, "Por favor, selecione o sexo.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedGenero = findViewById(selectedGeneroId);
            String genero = selectedGenero.getText().toString();

            // Obtém o nível de atividade física selecionado
            int selectedNivelId = nivelAtividadeGroup.getCheckedRadioButtonId();
            if (selectedNivelId == -1) {
                Toast.makeText(this, "Por favor, selecione o nível de atividade física.", Toast.LENGTH_SHORT).show();
                return;
            }

            double fatorAtividade;
            switch (selectedNivelId) {
                case R.id.radio_baixo:
                    fatorAtividade = 1.2;
                    break;
                case R.id.radio_moderado:
                    fatorAtividade = 1.55;
                    break;
                case R.id.radio_alto:
                    fatorAtividade = 1.725;
                    break;
                default:
                    fatorAtividade = 1.0;
                    break;
            }

            // Calcula a TMB
            double tmb;
            if (genero.equalsIgnoreCase("masculino")) {
                tmb = 88.36 + (13.4 * peso) + (4.8 * altura) - (5.7 * idade);
            } else {
                tmb = 447.6 + (9.2 * peso) + (3.1 * altura) - (4.3 * idade);
            }

            double tmbFinal = tmb * fatorAtividade;

            // Passa os dados para a nova Activity
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("TMB_RESULTADO", tmbFinal);
            startActivity(intent);

            // Salva os dados no Firebase
            usuario.setAge(idade);
            usuario.setWeight(peso);
            usuario.setHeight(altura);
            usuario.setGender(genero);

            int selectedObjetivoId = objetivoGroup.getCheckedRadioButtonId();
            if (selectedObjetivoId != -1) {
                RadioButton selectedObjetivo = findViewById(selectedObjetivoId);
                usuario.setGoal(selectedObjetivo.getText().toString());
            }

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            reference.child(usuario.getEmail().replace(".", "_")).setValue(usuario);

            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a Activity após o cadastro
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}