package br.com.eucolaborador.model.entity;

import java.io.Serializable;
import java.util.List;

public class Endemia implements Serializable {
    private int id;
    private String nome;
    private List<TipoFoco> tiposFoco;

    public Endemia(int id, String nome, List<TipoFoco> tiposFoco) {
        this.id = id;
        this.nome = nome;
        this.tiposFoco = tiposFoco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<TipoFoco> getTiposFocos() {
        return tiposFoco;
    }
}
