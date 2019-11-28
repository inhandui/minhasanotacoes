package com.cursoandroid.minhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText txtAnotacoes;
    private ImageView btnSalvar;

    private static final String ARQUIVO_ANOTACOES = "arqAnotacoes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAnotacoes = findViewById(R.id.txtAnotacoes);

        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarArquivo(txtAnotacoes.getText().toString());
            }
        });

    }

    private void gravarArquivo(String texto) {

        try{
            OutputStreamWriter outputSW = new OutputStreamWriter( openFileOutput(ARQUIVO_ANOTACOES, Context.MODE_PRIVATE));
            outputSW.write(texto);
            outputSW.close();

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }
    }


}
