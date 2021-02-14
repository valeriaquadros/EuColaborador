package br.com.eucolaborador.model.dao;

import android.content.Context;

import br.com.eucolaborador.model.entity.DenunciaFoco;
import br.com.eucolaborador.model.util.ServerCallback;

import java.util.HashMap;

public class DenunciaFocoDAO extends Dao {

    public DenunciaFocoDAO(Context context) {
        super(context, DenunciaFoco.class, "/DenunciaFoco/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param endemiaId      id da endemia da denuncia
     * @param enderecoId     id do endereço da denuncia
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(int enderecoId, int endemiaId, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("enderecoId", enderecoId);
        params.put("endemiaId", endemiaId);
        params.put("data", sdf.format(System.currentTimeMillis()));

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param id             id
     * @param endemiaId      id da endemia da denuncia
     * @param enderecoId     id do endereço da denuncia
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, int enderecoId, int endemiaId, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("enderecoId", enderecoId);
        params.put("endemiaId", endemiaId);
        params.put("data", sdf.format(System.currentTimeMillis()));

        super.update(id, params, serverCallback);
    }
}
