package com.example.euagenteandroid.model.entity;

import java.io.Serializable;

public class EnfermoEndereco implements Serializable {
    private int id;
    private int enfermoId;
    private Enfermo enfermo;
    private int enderecoId;
    private Endereco endereco;

    public EnfermoEndereco() {
    }

    public EnfermoEndereco(Endereco endereco) {
        this.enderecoId = endereco.getId();
        this.endereco = endereco;
    }

    public EnfermoEndereco(Enfermo enfermo, Endereco endereco) {
        this.enfermo = enfermo;
        this.endereco = endereco;
    }

    public EnfermoEndereco(int id, Enfermo enfermo, Endereco endereco) {
        this.id = id;
        this.enfermo = enfermo;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public Enfermo getEnfermo() {
        return enfermo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnfermoId() {
        return enfermoId;
    }

    public void setEnfermoId(int enfermoId) {
        this.enfermoId = enfermoId;
    }

    public void setEnfermo(Enfermo enfermo) {
        this.enfermo = enfermo;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
