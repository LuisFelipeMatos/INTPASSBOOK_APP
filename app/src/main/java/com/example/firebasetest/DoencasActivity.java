package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.firebasetest.model.Doencas;

public class DoencasActivity extends AppCompatActivity {


    private Spinner spinnerMP;
    private Spinner spinnerFT;
    private Spinner spinnerAT;
    private Spinner spinnerCB;
    private Spinner spinnerCP;
    private Spinner spinnerMA;
    private Spinner spinnerMC;
    private Spinner spinnerPB;
    private Button proximoDoencas;

    private String mP;
    private String fT;
    private String aT;
    private String cB;
    private String cP;
    private String mA;
    private String mC;
    private String pB;
    private String idRepeticaoDoencas;
    private String idCodAvaliacaoDoencas;
    private String estagioR7;

    Doencas doencas = new Doencas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doencas);

        spinnerMP = findViewById(R.id.spinnerMP);
        spinnerFT = findViewById(R.id.spinnerFT);
        spinnerAT = findViewById(R.id.spinnerAT);
        spinnerCB = findViewById(R.id.spinnerCB);
        spinnerCP = findViewById(R.id.spinnerCP);
        spinnerMA = findViewById(R.id.spinnerMA);
        spinnerMC = findViewById(R.id.spinnerMC);
        spinnerPB = findViewById(R.id.spinnerPB);

        proximoDoencas = findViewById(R.id.buttonProximoD);



        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Doenças");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idRepeticaoDoencas = getIntent().getStringExtra("idRepeticaoDoencas");
        idCodAvaliacaoDoencas = getIntent().getStringExtra("idAvaliacaoDoencas");
        estagioR7 = getIntent().getStringExtra("estagioDoencasR7");


        doencas.setId(idRepeticaoDoencas);
        doencas.setCod(idCodAvaliacaoDoencas);


        //MS - Moderadamente suscetível
        //S - Suscetível
        //R - Resistente
        //MR - Moderadamente resistente

        final String[] valueMP = {"Nenhuma","MS","S","MR","R"};
        final String[] valueFT = {"Nenhuma","MS","S","MR","R"};
        final String[] valueAT = {"Nenhuma","MS","S","MR","R"};
        final String[] valueCB = {"Nenhuma","MS","S","MR","R"};
        final String[] valueCP = {"Nenhuma","MS","S","MR","R"};
        final String[] valueMA = {"Nenhuma","MS","S","MR","R"};
        final String[] valueMC = {"Nenhuma","MS","S","MR","R"};
        final String[] valuePB = {"Nenhuma","MS","S","MR","R"};


        ArrayAdapter<String> adapterMP = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueMP);
        adapterMP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterFT = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueFT);
        adapterFT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterAT = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueAT);
        adapterAT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCB = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueCB);
        adapterCB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCP = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueCP);
        adapterCP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterMA = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueMA);
        adapterMA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterMC = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueMC);
        adapterMC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterPB = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valuePB);
        adapterPB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        spinnerMP.setAdapter(adapterMP);
        spinnerFT.setAdapter(adapterFT);
        spinnerAT.setAdapter(adapterAT);
        spinnerCB.setAdapter(adapterCB);
        spinnerCP.setAdapter(adapterCP);
        spinnerMA.setAdapter(adapterMA);
        spinnerMC.setAdapter(adapterMC);
        spinnerPB.setAdapter(adapterPB);

        proximoDoencas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doencas.setEstagioR7(estagioR7);
                doencas.salvarDoencas();
                Intent intent = new Intent(DoencasActivity.this, ObsDoencasActivity.class);
                intent.putExtra("repeticaoObs", idRepeticaoDoencas);
                intent.putExtra("codObs", idCodAvaliacaoDoencas);
                intent.putExtra("estagioObs", estagioR7);
                startActivity(intent);


            }
        });

        spinnerMP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mP = valueMP[i];

                doencas.setMp(mP);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fT = valueFT[i];

                doencas.setFt(fT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aT = valueAT[i];

                doencas.setAt(aT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cB = valueCB[i];

                doencas.setCb(cB);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cP = valueCP[i];

                doencas.setCp(cP);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mA = valueMA[i];

                doencas.setMa(mA);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mC = valueMC[i];

                doencas.setMc(mC);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerPB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pB = valuePB[i];

                doencas.setPb(pB);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}