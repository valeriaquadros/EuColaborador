package com.example.euagenteandroid.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enfermo implements Serializable {
    private int id;
    private boolean gestante;
    private Date data;
    private int endemiaId;
    private Endemia endemia;
    private int pessoaId;
    private Pessoa pessoa;
    private List<EnfermoEndereco> enfermoEndereco;

    public Enfermo() {
        enfermoEndereco = new ArrayList<>();
    }

    public Enfermo(int id, boolean gestante, Date data, int endemiaId, Endemia endemia, int pessoaId, Pessoa pessoa, List<EnfermoEndereco> enfermoEndereco) {
        this.id = id;
        this.gestante = gestante;
        this.data = data;
        this.endemiaId = endemiaId;
        this.endemia = endemia;
        this.pessoaId = pessoaId;
        this.pessoa = pessoa;
        this.enfermoEndereco = enfermoEndereco;
    }

    public int getId() {
        return id;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Endemia getEndemia() {
        return endemia;
    }

    public void setEndemia(Endemia endemia) {
        this.endemia = endemia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEndemiaId() {
        return endemiaId;
    }

    public void setEndemiaId(int endemiaId) {
        this.endemiaId = endemiaId;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public List<EnfermoEndereco> getEnfermoEndereco() {
        return enfermoEndereco;
    }

    public void setEnfermoEndereco(List<EnfermoEndereco> enfermoEndereco) {
        this.enfermoEndereco = enfermoEndereco;
    }

    @Override
    public String toString() {
        return "Enfermo{" +
                "id=" + id +
                ", endemiaId=" + endemiaId +
                ", pessoaId=" + pessoaId +
                '}';
    }
}
