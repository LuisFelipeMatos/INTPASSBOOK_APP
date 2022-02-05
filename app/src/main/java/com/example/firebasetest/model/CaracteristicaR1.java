package com.example.firebasetest.model;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.SplittableRandom;

public class CaracteristicaR1 {
    private String id;
    private String codAva;
    private String stand;
    private String dtr1;
    private String cf;
    private String estagioR1;

    public CaracteristicaR1() {
    }

    public void salvarCaracteristica(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference avaliacao = databaseReference.child("avaliacoes").child(getCodAva()).child("Repeticao "+ getId()).child("Caracteristicas "+getEstagioR1());
        avaliacao.setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Exclude
    public String getCodAva() {
        return codAva;
    }

    public void setCodAva(String codAva) {
        this.codAva = codAva;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getDtr1() {
        return dtr1;
    }

    public void setDtr1(String dtr1) {
        this.dtr1 = dtr1;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
    @Exclude
    public String getEstagioR1() {
        return estagioR1;
    }

    public void setEstagioR1(String estagioR1) {
        this.estagioR1 = estagioR1;
    }
}
