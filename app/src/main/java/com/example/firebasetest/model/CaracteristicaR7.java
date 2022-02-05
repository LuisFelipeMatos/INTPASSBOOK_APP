package com.example.firebasetest.model;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.Map;

public class CaracteristicaR7 {


    private String id;
    private String codAva;
    private String dtr7;
    private String dtr8;
    private String cp;
    private String visual;
    private String acm;
    private String alt;
    private String eng;
    private String estagioR7;

    public CaracteristicaR7() {
    }

    public void salvarCaracteristica(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference avaliacao = databaseReference.child("avaliacoes").child(getCodAva()).child("Repeticao "+ getId()).child("Caracteristicas " + getEstagioR7());
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

    public String getDtr7() {
        return dtr7;
    }

    public void setDtr7(String dtr7) {
        this.dtr7 = dtr7;
    }

    public String getDtr8() {
        return dtr8;
    }

    public void setDtr8(String dtr8) {
        this.dtr8 = dtr8;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public String getAcm() {
        return acm;
    }

    public void setAcm(String acm) {
        this.acm = acm;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }
    @Exclude
    public String getEstagioR7() {
        return estagioR7;
    }

    public void setEstagioR7(String estagioR7) {
        this.estagioR7 = estagioR7;
    }
}
