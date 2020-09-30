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
    private String sPeso;
    private String sAltura;
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
                if(!validaCampos()) return;

                double peso = Double.parseDouble(editPeso.getText().toString());
                double altura = Double.parseDouble(editAltura.getText().toString());

                double imc = calculaIMC(peso, altura);
                enviaResultado(imc);
            }
        });
    }

    private boolean validaCampos() {
        if (editPeso.getText().toString().isEmpty()) {
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

        return true;
    }

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


    private double calculaIMC(double peso, double altura) {
        double resultado = peso / (altura*altura);
        return resultado;
    }

    private void carregaCampos() {
        editAltura = findViewById(R.id.editTextAltura);
        editPeso = findViewById(R.id.editTextPeso);

        sPeso = editPeso.getText().toString();
        sAltura = editAltura.getText().toString();
        btnCalcular = findViewById(R.id.buttonCalcular);
    }
}
