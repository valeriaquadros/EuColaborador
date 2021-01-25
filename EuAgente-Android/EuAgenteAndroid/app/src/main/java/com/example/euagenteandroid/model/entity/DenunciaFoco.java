package com.example.euagenteandroid.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DenunciaFoco implements Serializable {
    private int id;
    private Date data;
    private int enderecoId;
    private Endereco endereco;
    private int endemiaId;
    private Endemia endemia;

    public DenunciaFoco(int id, Date data, int enderecoId, Endereco endereco, int endemiaId, Endemia endemia) {
        this.id = id;
        this.data = data;
        this.enderecoId = enderecoId;
        this.endereco = endereco;
        this.endemiaId = endemiaId;
        this.endemia = endemia;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endemia getEndemia() {
        return endemia;
    }

    public void setEndemia(Endemia endemia) {
        this.endemia = endemia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public int getEndemiaId() {
        return endemiaId;
    }

    public void setEndemiaId(int endemiaId) {
        this.endemiaId = endemiaId;
    }
}
