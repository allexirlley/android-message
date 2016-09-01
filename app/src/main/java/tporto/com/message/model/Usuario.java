package tporto.com.message.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.Firebase;

import tporto.com.message.application.ConfiguracaoFirebase;

/**
 * Created by tportopc on 31/08/16.
 */
@JsonIgnoreProperties({"id","senha"})
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public void salvar(){
        Firebase firebase = ConfiguracaoFirebase.getFirebase();
        firebase = firebase.child("usuarios").child(getId());
        firebase.setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
