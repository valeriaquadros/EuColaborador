package com.example.euagenteandroid.model.dao;

import android.content.Context;

import com.example.euagenteandroid.model.entity.Endemia;
import com.example.euagenteandroid.model.util.ServerCallback;

import java.util.HashMap;

public class EndemiaDAO extends Dao {

    public EndemiaDAO(Context context) {
        super(context, Endemia.class, "/Endemia/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param nome           nome
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(String nome, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nome", nome);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param id             id
     * @param nome           nome
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, String nome, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nome", nome);

        super.update(id, params, serverCallback);
    }

    /**
     * Retorna lista de objeto cadastrados através de serverCallBack
     * Retorna lista caso sucesso
     * Retorna falha caso falha
     *
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void getListFocosByEndemiaId(int endemiaId, ServerCallback serverCallback) {
        super.getById(endemiaId, serverCallback);
    }
}
