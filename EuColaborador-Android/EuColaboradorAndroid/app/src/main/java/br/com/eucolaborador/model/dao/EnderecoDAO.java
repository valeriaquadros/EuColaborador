package br.com.eucolaborador.model.dao;

import android.content.Context;
import android.os.Looper;

import br.com.eucolaborador.model.entity.Endereco;
import br.com.eucolaborador.model.util.ServerCallback;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import java.util.HashMap;

public class EnderecoDAO extends Dao{

    public EnderecoDAO(Context context) {
        super(context, Endereco.class, "/Endereco/");
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param logradouro logradouro
     * @param numero numero
     * @param bairro  bairro
     * @param cidade cidade
     * @param estado estado
     * @param cep cep
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(String logradouro, String numero, String bairro, String cidade, String estado, String cep, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("logradouro", logradouro);
        params.put("numero", numero);
        params.put("bairro", bairro);
        params.put("cidade", cidade);
        params.put("estado", estado);
        params.put("cep", cep);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param logradouro logradouro
     * @param numero numero
     * @param bairro  bairro
     * @param cidade cidade
     * @param estado estado
     * @param cep cep
     * @param latitude latitude
     * @param longitude longitude
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(String logradouro, String numero, String bairro, String cidade, String estado, String cep, String latitude, String longitude, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("logradouro", logradouro);
        params.put("numero", numero);
        params.put("bairro", bairro);
        params.put("cidade", cidade);
        params.put("estado", estado);
        params.put("cep", cep);
        params.put("latitude", latitude);
        params.put("longitude", longitude);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param coordenadaX coordenada X
     * @param coordenadaY coordenada Y
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void create(String coordenadaX, String coordenadaY, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("coordenadaX", coordenadaX);
        params.put("coordenadaY", coordenadaY);

        super.create(params, serverCallback);
    }

    /**
     * Método utilizado atualizar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id
     * @param logradouro logradouro
     * @param numero numero
     * @param bairro  bairro
     * @param cidade cidade
     * @param estado estado
     * @param cep cep
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void update(int id, String logradouro, String numero, String bairro, String cidade, String estado, String cep, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("logradouro", logradouro);
        params.put("numero", numero);
        params.put("bairro", bairro);
        params.put("cidade", cidade);
        params.put("estado", estado);
        params.put("cep", cep);

        super.update(id, params, serverCallback);
    }

    /**
     * Método utilizado criar objeto no banco. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id
     * @param coordenadaX coordenada X
     * @param coordenadaY coordenada Y
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     * @implNote
     */
    public void update(int id, String coordenadaX, String coordenadaY, ServerCallback serverCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("coordenadaX", coordenadaX);
        params.put("coordenadaY", coordenadaY);

        super.update(id, params, serverCallback);
    }

    public void getEnderecoByCep(String cep, ServerCallback serverCallback) {
        ViaCEPClient client = new ViaCEPClient();
        new Thread(() -> {
            Looper.prepare();
            try {
                ViaCEPEndereco viaCEPEndereco = client.getEndereco(cep);
                serverCallback.onResponse(true, new Endereco(viaCEPEndereco));
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
