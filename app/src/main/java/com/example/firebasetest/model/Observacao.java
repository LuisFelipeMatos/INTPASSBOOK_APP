package com.example.firebasetest.model;

import android.webkit.JavascriptInterface;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Observacao {

    private String descricao;
    private String idR1;
    private String codR1;
    private String estagioR1;

    public Observacao() {
    }

    public void salvarObservacao(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference observacao = databaseReference.child("avaliacoes").child(getCodR1()).child("Repeticao "+ getIdR1()).child("Caracteristicas "+ getEstagioR1()).child("Observacao");
        observacao.setValue(this);
    }




    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Exclude
    public String getIdR1() {
        return idR1;
    }

    public void setIdR1(String idR1) {
        this.idR1 = idR1;
    }
    @Exclude
    public String getCodR1() {
        return codR1;
    }

    public void setCodR1(String codR1) {
        this.codR1 = codR1;
    }
    @Exclude
    public String getEstagioR1() {
        return estagioR1;
    }

    public void setEstagioR1(String estagioR1) {
        this.estagioR1 = estagioR1;
    }
}
