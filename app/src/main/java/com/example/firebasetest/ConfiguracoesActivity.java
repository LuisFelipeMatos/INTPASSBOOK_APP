package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebasetest.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfiguracoesActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button botaoAlterarSenha;
    private Button botaoIniciaAltera;
    private TextView textnome;
    private EditText editAltera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofiguracoes);
        botaoAlterarSenha = findViewById(R.id.idButtonAlterarSenha);
        botaoIniciaAltera = findViewById(R.id.idButtonIniciaAltera);
        editAltera = findViewById(R.id.editTextAltera);
        textnome = findViewById(R.id.textViewNome);

        botaoIniciaAltera.setVisibility(View.INVISIBLE);
        editAltera.setVisibility(View.INVISIBLE);


        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Configurações");
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = UsuarioFirebase.getUsuarioAtual();
        textnome.setText(firebaseUser.getDisplayName());


        botaoAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoIniciaAltera.setVisibility(View.VISIBLE);
                editAltera.setVisibility(View.VISIBLE);
            }
        });

        botaoIniciaAltera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(editAltera.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(ConfiguracoesActivity.this, "Alteração de senha iniciada. Email enviado!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ConfiguracoesActivity.this, "Falha! Tente novamente!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

    }
}