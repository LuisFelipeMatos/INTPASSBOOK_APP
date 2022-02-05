package com.example.firebasetest.model;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private String nomePropriedade;
    private String idAvaliacao;
    private String cidade;
    private String uf;
    private String safra;
    private String ensaio;
    private String talhao;
    private String bloco;
    private String parcela;
    private String genotipo;
    private String tipoEnsaio;
    private String categoria;
    private String epoca;
    private String dataSemeadura;
    private String repeticao;
    private String estagio;


    public Avaliacao() {
    }

    public void salvarAvalicao(){

        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference avaliacao = databaseReference.child("avaliacoes").child(getIdAvaliacao()).child("Repeticao "+ getRepeticao());
        avaliacao.setValue(this);

    }


    public String getNomePropriedade() {
        return nomePropriedade;
    }

    public void setNomePropriedade(String nomePropriedade) {
        this.nomePropriedade = nomePropriedade;
    }
    @Exclude
    public String getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(String idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public String getEnsaio() {
        return ensaio;
    }

    public void setEnsaio(String ensaio) {
        this.ensaio = ensaio;
    }

    public String getTalhao() {
        return talhao;
    }

    public void setTalhao(String talhao) {
        this.talhao = talhao;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getGenotipo() {
        return genotipo;
    }

    public void setGenotipo(String genotipo) {
        this.genotipo = genotipo;
    }

    public String getTipoEnsaio() {
        return tipoEnsaio;
    }

    public void setTipoEnsaio(String tipoEnsaio) {
        this.tipoEnsaio = tipoEnsaio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getDataSemeadura() {
        return dataSemeadura;
    }

    public void setDataSemeadura(String dataSemeadura) {
        this.dataSemeadura = dataSemeadura;
    }
    @Exclude
    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }
}
