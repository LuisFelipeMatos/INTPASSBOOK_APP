package com.example.firebasetest.model;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Doencas {
    private String id;
    private String cod;
    private String mp;
    private String ft;
    private String at;
    private String cb;
    private String cp;
    private String ma;
    private String mc;
    private String pb;
    private String estagioR7;

    public Doencas() {
    }

    public void salvarDoencas(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference doenca = databaseReference.child("avaliacoes").child(getCod()).child("Repeticao "+ getId()).child("Caracteristicas " + getEstagioR7()).child("Doencas");
        doenca.setValue(this);
    }
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Exclude
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getFt() {
        return ft;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }
    @Exclude
    public String getEstagioR7() {
        return estagioR7;
    }

    public void setEstagioR7(String estagioR7) {
        this.estagioR7 = estagioR7;
    }
}
