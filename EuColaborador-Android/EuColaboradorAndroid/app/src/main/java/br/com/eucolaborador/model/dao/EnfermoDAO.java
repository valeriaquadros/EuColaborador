package br.com.eucolaborador.model.dao;

import android.content.Context;

import br.com.eucolaborador.model.entity.Endereco;
import br.com.eucolaborador.model.entity.Enfermo;
import br.com.eucolaborador.model.util.ServerCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnfermoDAO extends Dao {

    public EnfermoDAO(Context context) {
        super(context, Enfermo.class, "/Enfermo/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param endemiaId      id da endemia do doente
     * @param gestante       informação se a pessoa é gestante ou não
     * @param pessoaId       id da pessoa doente
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(boolean gestante, int endemiaId, int pessoaId, List<Endereco> enderecos, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("gestante", gestante);
        params.put("data", sdf.format(System.currentTimeMillis()));
        params.put("endemiaId", endemiaId);
        params.put("pessoaId", pessoaId);
        List<Integer> enfermoEnderecos = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enfermoEnderecos.add(endereco.getId());
        }
        params.put("Enderecos", enfermoEnderecos);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     *
     * @param id             id
     * @param endemiaId      id da endemia do doente
     * @param gestante       informação se a pessoa é gestante ou não
     * @param pessoaId       id da pessoa doente
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, boolean gestante, int endemiaId, int pessoaId, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("gestante", gestante);
        params.put("date", sdf.format(System.currentTimeMillis()));
        params.put("endemiaId", endemiaId);
        params.put("pessoaId", pessoaId);

        super.update(id, params, serverCallback);
    }
}
