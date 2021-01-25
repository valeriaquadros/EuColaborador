package com.example.euagenteandroid.model.dao;

import android.content.Context;

import com.example.euagenteandroid.model.entity.ChecklistEndereco;
import com.example.euagenteandroid.model.util.ServerCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChecklistEnderecoDAO extends Dao {

    public ChecklistEnderecoDAO(Context context) {
        super(context, ChecklistEndereco.class, "/ChecklistEndereco/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param enderecoId     id do endereço
     * @param tipoFocos      lista de ids do tipo de foco selecionados
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(int enderecoId, List<Integer> tipoFocos, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        String data = sdf.format(System.currentTimeMillis());
        List<ChecklistEndereco> checklistEnderecos = new ArrayList<>();
        for (int tipoFocoId : tipoFocos) {
            checklistEnderecos.add(new ChecklistEndereco(enderecoId, tipoFocoId, data));
        }
        params.put("checklistEndereco", gson.toJson(checklistEnderecos));
        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param id             id
     * @param enderecoId     id do endereço
     * @param tipoFocoId     id do tipo de foco
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, String enderecoId, int tipoFocoId, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("enderecoId", enderecoId);
        params.put("tipoFocoId", tipoFocoId);
        params.put("data", sdf.format(System.currentTimeMillis()));

        super.create(params, serverCallback);
    }
}
