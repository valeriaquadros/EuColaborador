package br.com.eucolaborador.model.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import br.com.eucolaborador.model.entity.Pessoa;
import br.com.eucolaborador.model.util.Constants;
import br.com.eucolaborador.model.util.ServerCallback;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PessoaDAO extends Dao{

    public PessoaDAO(Context context) {
        super(context, Pessoa.class, "/Pessoa/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param nome nome
     * @param cpf cpf
     * @param dataNascimento data nascimento
     * @param sexo sexo
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(String nome, String cpf, Date dataNascimento, char sexo, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nome", nome);
        params.put("cpf", cpf);
        params.put("dataNascimento", sdf.format(dataNascimento));
        params.put("sexo", sexo);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id
     * @param nome nome
     * @param cpf cpf
     * @param dataNascimento data nascimento
     * @param sexo sexo
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, String nome, String cpf, Date dataNascimento, char sexo, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nome", nome);
        params.put("cpf", cpf);
        params.put("dataNascimento", sdf.format(dataNascimento));
        params.put("sexo", sexo);

        super.update(id, params, serverCallback);
    }

    /**
     * Retorna objeto cadastrado através de serverCallBack
     * Retorna lista caso sucesso
     * Retorna falha caso falha
     *
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void getPessoaByCpf(String cpf, ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + "cpf/" + cpf;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                endPoint,
                response -> {
                    Object object = gson.fromJson(response, requestClass);
                    serverCallback.onResponse(true, object);
                },
                error -> serverCallback.onResponse(false, null)) {
            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }
}
