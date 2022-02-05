package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.example.firebasetest.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.idLogEmail);
        campoSenha = findViewById(R.id.idLogSenha);
        botaoLogar = findViewById(R.id.idBtnLogin);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();

                if(!textEmail.isEmpty()){
                    if(!textSenha.isEmpty()){
                        usuario = new Usuario();
                        usuario.setEmail(textEmail);
                        usuario.setSenha(textSenha);
                        validarLogin();

                    }else{

                        Toast.makeText(LoginActivity.this,
                                "Preencha o campo Senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{



                    Toast.makeText(LoginActivity.this,
                            "Preencha o campo Email!",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){

                  Toast.makeText(LoginActivity.this,
                          "Sucesso ao fazer login!",
                          Toast.LENGTH_SHORT).show();
                  abrirTelaPrincipal();
              }else{

                  String excecao = "";
                  try{
                      throw task.getException();
                  }catch(FirebaseAuthInvalidCredentialsException e){
                      excecao = "E-mail e senha não correspondem a um usuário cadastrado!";

                  }catch(FirebaseAuthInvalidUserException e){
                    excecao = "Usuário não está cadastrado!";

                  }catch (Exception e){
                    excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                      e.printStackTrace();
                  }


                  Toast.makeText(LoginActivity.this,
                          excecao,
                          Toast.LENGTH_SHORT).show();
              }
            }
        });

    }
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, TelaPrincipal.class));
        finish();
    }
    public void abrirTelaCadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }
}
