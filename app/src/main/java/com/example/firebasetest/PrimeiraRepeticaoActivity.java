package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firebasetest.model.Avaliacao;
import com.example.firebasetest.model.CaracteristicaR1;
import com.example.firebasetest.model.CaracteristicaR7;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrimeiraRepeticaoActivity extends AppCompatActivity {


    private Spinner spinnerStand;
    private Spinner spinnerCF;
    private Spinner spinnerCP;
    private Spinner spinnerVisual;
    private Spinner spinnerACM;
    private Spinner spinnerENG;
    private Spinner spinnerEstagio;

    private String stand;
    private String cF;
    private String cP;
    private String visual;
    private String aCM;
    private String eNG;
    private String dTR1 ;
    private String dTR7 ;
    private String dTR8 ;
    private String repeticaoId;
    private String avaliacaoId;
    private String estagio;




    private Button buttonProximo;
    private EditText editDTR1;
    private EditText editDTR7;
    private EditText editDTR8;
    private EditText editALT;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeirarepeticao);

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Caracteriscas");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerStand = findViewById(R.id.spinner);
        buttonProximo = findViewById(R.id.idBotaoProximoC);
        spinnerCF = findViewById(R.id.spinner3);
        spinnerCP = findViewById(R.id.spinner6);
        spinnerVisual = findViewById(R.id.spinner7);
        spinnerACM = findViewById(R.id.spinner8);
        spinnerENG = findViewById(R.id.spinner10);
        editDTR1 = findViewById(R.id.editTextR1);
        editDTR7 = findViewById(R.id.editTextR7);
        editDTR8 = findViewById(R.id.editTextR8);
        editALT = findViewById(R.id.editTextALT);
        spinnerEstagio = findViewById(R.id.spinnerEstagio);

        SimpleMaskFormatter r1 = new SimpleMaskFormatter("NN/NN/NN");
        MaskTextWatcher rr1 = new MaskTextWatcher(editDTR1, r1);
        editDTR1.addTextChangedListener(rr1);

        SimpleMaskFormatter r7 = new SimpleMaskFormatter("NN/NN/NN");
        MaskTextWatcher rr7 = new MaskTextWatcher(editDTR7, r7);
        editDTR7.addTextChangedListener(rr7);

        SimpleMaskFormatter r8 = new SimpleMaskFormatter("NN/NN/NN");
        MaskTextWatcher rr8 = new MaskTextWatcher(editDTR8, r8);
        editDTR8.addTextChangedListener(rr8);


        CaracteristicaR1 caracteristicaR1 = new CaracteristicaR1();
        CaracteristicaR7 caracteristicaR7 = new CaracteristicaR7();
        Avaliacao avaliacao = new Avaliacao();



        repeticaoId = getIntent().getStringExtra("idRepeticao");
        avaliacaoId = getIntent().getStringExtra("idAvaliacao");

        caracteristicaR1.setId(repeticaoId);
        caracteristicaR1.setCodAva(avaliacaoId);

        caracteristicaR7.setId(repeticaoId);
        caracteristicaR7.setCodAva(avaliacaoId);








        //instancioando arraylist

        final String[] valueStand = {"Selecionar","Ruim","Regular", "Bom", "Ótimo"};
        final String[] valueCF = {"Selecionar", "Roxa", "Branca", "Mista"};
        final String[] valueCP = {"Selecionar", "Cinza", "Marrom"};
        final String[] valueVisual = {"Selecionar","Ruim","Regular", "Bom", "Ótimo"};
        final String[] valueACM = {"Selecionar","0%","25%","50%","75%","100%"};
        final String[] valueENG = {"Selecionar","Ruim","Regular", "Bom", "Ótimo"};
        final String[] valueEstagio = {"Selecionar","R1", "R7"};


        //sentadno a Arraylist no spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, valueStand);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCF = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,valueCF);
        adapterCF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCP = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valueCP);
        adapterCP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterVisual = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valueVisual);
        adapterVisual.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterACM = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valueACM);
        adapterACM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterENG = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valueENG);
        adapterACM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterEstagio = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,valueEstagio );
        adapterEstagio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCF.setAdapter(adapterCF);
        spinnerStand.setAdapter(adapter);
        spinnerCP.setAdapter(adapterCP);
        spinnerVisual.setAdapter(adapterVisual);
        spinnerACM.setAdapter(adapterACM);
        spinnerENG.setAdapter(adapterENG);
        spinnerEstagio.setAdapter(adapterEstagio);


        spinnerStand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stand = valueStand[i];

                caracteristicaR1.setStand(stand);



                 }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cF = valueCF[i];
                caracteristicaR1.setCf(cF);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cP = valueCP[i];
                caracteristicaR7.setCp(cP);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerVisual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                visual = valueVisual[i];

                caracteristicaR7.setVisual(visual);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerACM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aCM = valueACM[i];

                caracteristicaR7.setAcm(aCM);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerENG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                eNG = valueENG[i];

                caracteristicaR7.setEng(eNG);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEstagio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                estagio = valueEstagio[i];



                if(estagio == "R1"){
                    caracteristicaR1.setEstagioR1(estagio);
                    spinnerStand.setVisibility(View.VISIBLE);
                    spinnerCF.setVisibility(View.VISIBLE);
                    editDTR1.setVisibility(View.VISIBLE);

                    editDTR7.setVisibility(View.INVISIBLE);
                    editDTR8.setVisibility(View.INVISIBLE);
                    spinnerCP.setVisibility(View.INVISIBLE);
                    spinnerVisual.setVisibility(View.INVISIBLE);
                    spinnerACM.setVisibility(View.INVISIBLE);
                    editALT.setVisibility(View.INVISIBLE);
                    spinnerENG.setVisibility(View.INVISIBLE);



                }else if(estagio == "R7"){
                    caracteristicaR7.setEstagioR7(estagio);
                    spinnerStand.setVisibility(View.INVISIBLE);
                    spinnerCF.setVisibility(View.INVISIBLE);
                    editDTR1.setVisibility(View.INVISIBLE);

                    editDTR7.setVisibility(View.VISIBLE);
                    editDTR8.setVisibility(View.VISIBLE);
                    spinnerCP.setVisibility(View.VISIBLE);
                    spinnerVisual.setVisibility(View.VISIBLE);
                    spinnerACM.setVisibility(View.VISIBLE);
                    editALT.setVisibility(View.VISIBLE);
                    spinnerENG.setVisibility(View.VISIBLE);


                }
                else{
                    spinnerStand.setVisibility(View.INVISIBLE);
                    spinnerCF.setVisibility(View.INVISIBLE);
                    editDTR1.setVisibility(View.INVISIBLE);
                    editDTR7.setVisibility(View.INVISIBLE);
                    editDTR8.setVisibility(View.INVISIBLE);
                    spinnerCP.setVisibility(View.INVISIBLE);
                    spinnerVisual.setVisibility(View.INVISIBLE);
                    spinnerACM.setVisibility(View.INVISIBLE);
                    editALT.setVisibility(View.INVISIBLE);
                    spinnerENG.setVisibility(View.INVISIBLE);

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        buttonProximo.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                if(estagio == "R1"){
                    caracteristicaR1.setDtr1(editDTR1.getText().toString());
                    DatabaseReference reference = databaseReference.child("avaliacoes").child(avaliacaoId).child("Repeticao "+ repeticaoId).child("Caracteristicas "+estagio);

                    caracteristicaR1.salvarCaracteristica();


                    Intent intent = new Intent(PrimeiraRepeticaoActivity.this, ObservacaoActivity.class);
                    intent.putExtra("idRepeticaoObeservacao", repeticaoId);
                    intent.putExtra("idAvaliacaoObservacao", avaliacaoId);
                    intent.putExtra("estagioObservacaoR1", estagio);
                    startActivity(intent);
                    finish();

                }else if(estagio == "R7"){

                    caracteristicaR7.setDtr7(editDTR7.getText().toString());
                    caracteristicaR7.setDtr8(editDTR8.getText().toString());
                    caracteristicaR7.setAlt(editALT.getText().toString());
                    caracteristicaR7.salvarCaracteristica();
                    Intent intent = new Intent(PrimeiraRepeticaoActivity.this, DoencasActivity.class);
                    intent.putExtra("idRepeticaoDoencas", repeticaoId);
                    intent.putExtra("idAvaliacaoDoencas", avaliacaoId);
                    intent.putExtra("estagioDoencasR7", estagio);


                    startActivity(intent);
                    finish();
                }else{

                    Toast.makeText(PrimeiraRepeticaoActivity.this, "Selecione o Estágio para continuar!!", Toast.LENGTH_SHORT).show();
                }



            }
        });







    }

}