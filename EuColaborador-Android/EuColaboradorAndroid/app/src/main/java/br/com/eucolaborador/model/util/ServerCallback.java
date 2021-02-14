package br.com.eucolaborador.model.util;

public interface ServerCallback {
    void onResponse(boolean success, Object response);
}