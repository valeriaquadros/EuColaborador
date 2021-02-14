package br.com.eucolaborador.model.entity;

import java.io.Serializable;

public class UserToken implements Serializable {
    private Usuario usuario;
    private String token;

    public UserToken(Usuario usuario, String token) {
        this.usuario = usuario;
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getToken() {
        return token;
    }
}
