package br.com.eucolaborador.model.entity;

import java.io.Serializable;

public class TipoFoco implements Serializable {
    private int id;
    private String descricao;

    public TipoFoco(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
