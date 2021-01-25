package com.example.euagenteandroid.model.entity;

import java.io.Serializable;

public class ChecklistEndereco implements Serializable {
    private String data;
    private int enderecoId;
    private int tipoFocoId;

    public ChecklistEndereco(int enderecoId, int tipoFocoId, String data) {
        this.data = data;
        this.enderecoId = enderecoId;
        this.tipoFocoId = tipoFocoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public int getTipoFocoId() {
        return tipoFocoId;
    }

    public void setTipoFocoId(int tipoFocoId) {
        this.tipoFocoId = tipoFocoId;
    }

}
