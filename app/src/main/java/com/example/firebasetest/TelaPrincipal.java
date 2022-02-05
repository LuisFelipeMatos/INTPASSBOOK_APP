package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.example.firebasetest.model.Avaliacao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class TelaPrincipal extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private ArrayList<Avaliacao> listaAvaliacao;
    private ValueEventListener valueEventListenerAvaliacao;
    private boolean firebaseOffline = false;
    private Button botaoIniciar;
    private DatabaseReference database;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("INTPASSBOOK");
        setSupportActionBar(toolbar);
        botaoIniciar = findViewById(R.id.idIniciarAvaliacao);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            Toast.makeText(TelaPrincipal.this,"App Conectado a internet!", Toast.LENGTH_SHORT).show();
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(TelaPrincipal.this,"App Conectado no Wifi!", Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(TelaPrincipal.this,"App Conectado ao dados moveis!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(TelaPrincipal.this,"App Desconectado!", Toast.LENGTH_SHORT).show();
        }




       /* DatabaseReference connectRef = FirebaseDatabase.getInstance().getReference(".info/connected");

        connectRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean conected = dataSnapshot.getValue(Boolean.class);
                if(conected){
                    Toast.makeText(TelaPrincipal.this,"App Conectado!", Toast.LENGTH_SHORT).show();
                }else if(!conected){
                    segundos();
                    Toast.makeText(TelaPrincipal.this,"App Desconectado!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaPrincipal.this, PreviewTalhaoActivity.class));

            }
        });

    }
   /* public void segundos(){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            Toast.makeText(TelaPrincipal.this,"teste", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuSair :
                deslogarUsuario();
                finish();
                break;

            case R.id.menuConfiguracoes :
                abrirConfiguracoes();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){
        try {
            autenticacao.signOut();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void abrirConfiguracoes(){
        Intent intent = new Intent(TelaPrincipal.this, ConfiguracoesActivity.class);
        startActivity(intent);
    }
    public void ativarfirebaseOffline(){

        try{
            if(!firebaseOffline){
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                firebaseOffline = true;
            }
            else{


            }

        }catch(Exception e){

        }

    }



}