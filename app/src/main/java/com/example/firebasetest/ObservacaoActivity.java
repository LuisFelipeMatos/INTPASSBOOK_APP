package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebasetest.model.Observacao;

public class ObservacaoActivity extends AppCompatActivity {
    private EditText editObservacao;

    private Button finalizaAvaliacao;

    private String idRepR1;
    private String codAvaR1;
    private String estagioR1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacao);

        editObservacao =  findViewById(R.id.editTextObservacao1);
        finalizaAvaliacao = findViewById(R.id.buttonFinalizar1);

        Observacao observacao = new Observacao();

        idRepR1 = getIntent().getStringExtra("idRepeticaoObeservacao");
        codAvaR1 = getIntent().getStringExtra("idAvaliacaoObservacao");
        estagioR1 = getIntent().getStringExtra("estagioObservacaoR1");




        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Observação");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        finalizaAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                observacao.setDescricao(editObservacao.getText().toString());

                observacao.setCodR1(codAvaR1);
                observacao.setIdR1(idRepR1);
                observacao.setEstagioR1(estagioR1);
                observacao.salvarObservacao();

                startActivity(new Intent(ObservacaoActivity.this, TelaPrincipal.class));
                finish();






            }
        });

    }
}