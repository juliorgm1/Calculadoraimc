package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editPeso;
    private EditText editAltura;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregaCampos();
        cliqueBotao();
    }

    private void cliqueBotao() {
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aqui validamos os campos do formulário, caso algum não passe na validação
                //o método retornará false.
                if(!validaCampos()) return;

                //Conseguimos capturar os dados apenas como string, para fazer o calculo é
                //necessário transformar a string para double com o metodo Double.parseDouble()
                double peso = Double.parseDouble(editPeso.getText().toString());
                double altura = Double.parseDouble(editAltura.getText().toString());

                //Calculando o IMC
                double imc = peso / (altura*altura);

                //Enviando o IMC para activity de exibição do resultado
                enviaResultado(imc);
            }
        });
    }

    private boolean validaCampos() {
        //O método isEmpty retorna TRUE se a string do EditText estiver vazia
        if (editPeso.getText().toString().isEmpty()) {
            //utilizamos o método setError para indicar ao usuário, algum problema no preechimento
            //do formulário
            editPeso.setError("Informe o peso");
            return false;
        }
        if (editAltura.getText().toString().isEmpty()) {
            editAltura.setError("Informe a altura");
            return false;
        }
        if (Double.parseDouble(editAltura.getText().toString())==0) {
            editAltura.setError("Altura deve ser maior que zero");
            return false;
        }

        if (Double.parseDouble(editAltura.getText().toString())>=3) {
            editAltura.setError("Altura deve ser superior a 3 Metros");
            return false;
        }

        return true;
    }

    //Esse metodo recebe o valor do imc e envia para ResultadoActivity
    private void enviaResultado(double imc) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("IMC", imc);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editPeso.setText("");
        editAltura.setText("");
        editPeso.requestFocus();
    }

    // Esse metodo calcula o IMC e retorna o resultado
    private double calculaIMC(double peso, double altura) {
        double resultado = peso / (altura*altura);
        return resultado;
    }

    //Esse método carrega os elementos do layout que serão manipulados
    private void carregaCampos() {
        editAltura = findViewById(R.id.editTextAltura);
        editPeso = findViewById(R.id.editTextPeso);
        btnCalcular = findViewById(R.id.buttonCalcular);
    }
}
