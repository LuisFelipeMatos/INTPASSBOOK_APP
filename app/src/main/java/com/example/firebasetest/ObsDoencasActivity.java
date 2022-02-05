package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebasetest.model.Obs;

public class ObsDoencasActivity extends AppCompatActivity {

    private EditText des;
    private Button finalizar;
    private Button cancelar;
    private String idRepR7;
    private String codAvaR7;
    private String estagioR7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacao_doencas);
        Obs obs = new Obs();
        des = findViewById(R.id.editTextObservacao1);
        finalizar = findViewById(R.id.buttonFinalizar1);


        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Observação ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idRepR7 = getIntent().getStringExtra("repeticaoObs");
        codAvaR7 = getIntent().getStringExtra("codObs");
        estagioR7 = getIntent().getStringExtra("estagioObs");




        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obs.setDescricaoR7(des.getText().toString());
                obs.setIdR7(idRepR7);
                obs.setCodR7(codAvaR7);
                obs.setEstagioR7(estagioR7);
                obs.salvarObservacao2();

                startActivity(new Intent(ObsDoencasActivity.this, TelaPrincipal.class));
                finish();
            }
        });

    }
}