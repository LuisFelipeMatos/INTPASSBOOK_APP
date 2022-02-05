package com.example.firebasetest.model;

import com.example.firebasetest.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Obs {

    private String descricaoR7;
    private String idR7;
    private String codR7;
    private String estagioR7;

    public Obs() {
    }
    public void salvarObservacao2(){
        DatabaseReference databaseReference1 = ConfiguracaoFirebase.getFirebaseDatabases();
        DatabaseReference observacao = databaseReference1.child("avaliacoes").child(getCodR7()).child("Repeticao "+ getIdR7()).child("Caracteristicas "+ getEstagioR7()).child("Doencas").child("Observacao");
        observacao.setValue(this);
    }

    public String getDescricaoR7() {
        return descricaoR7;
    }

    public void setDescricaoR7(String descricaoR7) {
        this.descricaoR7 = descricaoR7;
    }
    @Exclude
    public String getIdR7() {
        return idR7;
    }

    public void setIdR7(String idR7) {
        this.idR7 = idR7;
    }
    @Exclude
    public String getCodR7() {
        return codR7;
    }

    public void setCodR7(String codR7) {
        this.codR7 = codR7;
    }
    @Exclude
    public String getEstagioR7() {
        return estagioR7;
    }

    public void setEstagioR7(String estagioR7) {
        this.estagioR7 = estagioR7;
    }
}
