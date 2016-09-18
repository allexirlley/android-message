package tporto.com.message.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tportopc on 31/08/16.
 */
@IgnoreExtraProperties
public class Usuario {

    private DatabaseReference mDatabase;

    //@Exclude
    private String id;
    private String nome;
    private String email;
    //@Exclude
    private String senha;

    public Usuario() {
    }

    public void salvar(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("usuarios").child(getId()).setValue(this);
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
