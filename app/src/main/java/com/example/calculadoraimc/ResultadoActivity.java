package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultadoActivity extends AppCompatActivity {

    private TextView txtIMC;
    private TextView txtResultado;
    private Button btnRefazer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        carregaCampos();
        Intent intent = getIntent();
        double imc = intent.getDoubleExtra("IMC",0);

        txtIMC.setText(new DecimalFormat("##.##").format(imc));
        txtResultado.setText(pegaResultado(imc));

        btnRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String pegaResultado(double imc) {
        if (imc < 18.5 ) return "Você está abaixo do peso";
        else if (imc < 25 ) return "Você com peso normal";
        else if (imc < 30 ) return "Você está levemente acima do peso";
        else if (imc < 35 ) return "Você está com obesidade grau 1";
        else if (imc < 40 ) return "Você está com obesidade grau 2";
        else return "Você está com obesidade grau 3";
    }

    private void carregaCampos() {
        txtIMC = findViewById(R.id.textViewIMC);
        txtResultado = findViewById(R.id.textViewResultado);
        btnRefazer = findViewById(R.id.btnRefazer);
    }


}
