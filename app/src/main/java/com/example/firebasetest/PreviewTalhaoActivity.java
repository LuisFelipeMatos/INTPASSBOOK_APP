package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.firebasetest.model.Avaliacao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.concurrent.TimeUnit;

public class PreviewTalhaoActivity extends AppCompatActivity {
/*
FAZENDA TESTE; 21INT11046; Campo Mourão; PR; 20-21; 001; 1; 1A; 1109; 14/MO46.21; ELITE BT; TRANG; 1;  19/09/2020
 */
        private Button botaoQRCODE;
        private Button botaoProximo;
        private EditText editPropriedade;
        private EditText editEnsaio;
        private EditText editCidade;
        private EditText editEstado;
        private EditText editSafra;
        private EditText editCodAvaliacao;
        private EditText editTalhao;
        private EditText editBloco;
        private EditText editParcela;
        private EditText editGenotipo;
        private EditText editTipoEnsaio;
        private EditText editCategoria;
        private EditText editEpoca;
        private EditText editDataSemeadura;
        private EditText editRepeticao;
        private String info = null;
        private String repeticao = null;
        private String codAvaliacao = null;
        private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();





    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_talhao);
            Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
            toolbar.setTitle("Avaliação");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        botaoQRCODE = findViewById(R.id.idBotaoQRCODE);
        botaoProximo = findViewById(R.id.idBotaoProximo);
        botaoProximo.setVisibility(View.INVISIBLE);

        editPropriedade = findViewById(R.id.editTextPropriedade);
        editEnsaio = findViewById(R.id.editTextEnsaio);
        editCidade = findViewById(R.id.editTextCidade);
        editEstado = findViewById(R.id.editTextEstado);
        editSafra = findViewById(R.id.editTextSafra);
        editCodAvaliacao = findViewById(R.id.editTextCodAvaliacao);
        editTalhao = findViewById(R.id.editTextTalhao);
        editBloco = findViewById(R.id.editTextBloco);
        editParcela = findViewById(R.id.editTextParcela);
        editGenotipo = findViewById(R.id.editTextGenotipo);
        editTipoEnsaio = findViewById(R.id.editTextTipoEnsaio);
        editCategoria = findViewById(R.id.editTextCategoria);
        editEpoca = findViewById(R.id.editTextEpoca);
        editDataSemeadura = findViewById(R.id.editTextDataSemeadura);
        editRepeticao = findViewById(R.id.editTextRepeticao);

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setRepeticao(editRepeticao.getText().toString());




        botaoQRCODE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Avaliacao avaliacao = new Avaliacao();

                avaliacao.setRepeticao(editRepeticao.getText().toString());
                repeticao = avaliacao.getRepeticao();
                try {
                    if (avaliacao.getRepeticao().isEmpty()) {
                        Toast.makeText(PreviewTalhaoActivity.this, "Digite a repetição!", Toast.LENGTH_SHORT).show();
                    } else {

                        IntentIntegrator intentIntegrator = new IntentIntegrator(PreviewTalhaoActivity.this);
                        intentIntegrator.setPrompt("Aperte aumentar volume para usar o Flash!");

                        intentIntegrator.setBeepEnabled(true);
                        intentIntegrator.setOrientationLocked(true);
                        intentIntegrator.setCaptureActivity(Capture.class);
                        intentIntegrator.initiateScan();
                    }
                }catch (Exception e){}



            }
        });

        botaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (repeticao.equals("1")  || repeticao.equals("2") || repeticao.equals("3")) {
                    Intent intent = new Intent(PreviewTalhaoActivity.this, PrimeiraRepeticaoActivity.class);
                    intent.putExtra("idRepeticao", repeticao);
                    intent.putExtra("idAvaliacao", codAvaliacao);
                    startActivity(intent);

                } else {
                    Toast.makeText(PreviewTalhaoActivity.this,
                            "Número de repetição incorreto!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode,data
        );

        try {

            info = intentResult.getContents();

            final String[] values = info.split(";");

            Avaliacao avaliacao = new Avaliacao();

            avaliacao.setNomePropriedade(values[0]);
            avaliacao.setEnsaio(values[1]);
            avaliacao.setCidade(values[2]);
            avaliacao.setUf(values[3]);
            avaliacao.setSafra(values[4]);
            avaliacao.setIdAvaliacao(values[5]);
            avaliacao.setTalhao(values[6]);
            avaliacao.setBloco(values[7]);
            avaliacao.setParcela(values[8]);
            avaliacao.setGenotipo(values[9]);
            avaliacao.setTipoEnsaio(values[10]);
            avaliacao.setCategoria(values[11]);
            avaliacao.setEpoca(values[12]);
            avaliacao.setDataSemeadura(values[13]);
            avaliacao.setRepeticao(repeticao);
            codAvaliacao = avaliacao.getIdAvaliacao();


            editPropriedade.setText(avaliacao.getNomePropriedade());
            editEnsaio.setText(avaliacao.getEnsaio());
            editCidade.setText(avaliacao.getCidade());
            editEstado.setText(avaliacao.getUf());
            editSafra.setText(avaliacao.getSafra());
            editCodAvaliacao.setText(avaliacao.getIdAvaliacao());
            editTalhao.setText(avaliacao.getTalhao());
            editBloco.setText(avaliacao.getBloco());
            editParcela.setText(avaliacao.getParcela());
            editGenotipo.setText(avaliacao.getGenotipo());
            editTipoEnsaio.setText(avaliacao.getTipoEnsaio());
            editCategoria.setText(avaliacao.getCategoria());
            editEpoca.setText(avaliacao.getEpoca());
            editDataSemeadura.setText(avaliacao.getDataSemeadura());

            botaoProximo.setVisibility(View.VISIBLE);

            DatabaseReference reference = databaseReference.child("avaliacoes").child(avaliacao.getIdAvaliacao()).child("Repeticao "+ repeticao );

            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()){
                            avaliacao.salvarAvalicao();


                        }else{


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                reference.addListenerForSingleValueEvent(eventListener);


            } else {
                avaliacao.salvarAvalicao();

            }






        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void segundos(){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            Toast.makeText(PreviewTalhaoActivity.this,"é noissss", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}








