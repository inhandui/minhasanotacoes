package com.cursoandroid.minhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                if (gravarArquivo(txtAnotacoes.getText().toString())){
                    Toast.makeText(MainActivity.this, R.string.arquivo_ok, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.arquivo_erro, Toast.LENGTH_SHORT).show();
                }
            }
        });

        String conteudo_anotacoes = lerArquivo(ARQUIVO_ANOTACOES);
        if (conteudo_anotacoes != null){
            txtAnotacoes.setText(conteudo_anotacoes);
        }
        else {
            Toast.makeText(MainActivity.this, R.string.leitura_erro, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean gravarArquivo(String texto) {
        boolean resultado;
        try{
            OutputStreamWriter outputSW = new OutputStreamWriter( openFileOutput(ARQUIVO_ANOTACOES, Context.MODE_PRIVATE));
            outputSW.write(texto);
            outputSW.close();
            resultado = true;

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
            resultado = false;
        }

        return resultado;
    }


    private String lerArquivo(String nome){

        String resultado = "";

        try {
            //Open file
            InputStream inputS = openFileInput(ARQUIVO_ANOTACOES);

            if (inputS != null){
                //read file
                InputStreamReader inputSR = new InputStreamReader(inputS);
                //Get file buffer
                BufferedReader bufferedR = new BufferedReader(inputSR);
                //Recovery file content
                String linha = "";
                while( (linha = bufferedR.readLine()) != null ){
                    resultado += linha;
                }

                //close file
                inputS.close();
            }

        }catch (IOException e){
            Log.v("MainActivity", e.toString());
        }

        return resultado;
    }
}
