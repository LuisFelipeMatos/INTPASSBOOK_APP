package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnCadastro;

    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main
        );
    btnLogin = findViewById(R.id.button);


    btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            abrirLogin();

        }
    });




    }@Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();

    }



    public void abrirLogin(){
        startActivity(new Intent(this, LoginActivity.class));

    }

    public void verificarUsuarioLogado(){
    autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    //autenticacao.signOut();
    if(autenticacao.getCurrentUser() != null){
        abrirTelaPrincipal();
    }
    }
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, TelaPrincipal.class));
        finish();
    }
}