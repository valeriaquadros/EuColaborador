package br.com.eucolaborador.model.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import br.com.eucolaborador.model.entity.UserToken;
import br.com.eucolaborador.model.entity.Usuario;
import br.com.eucolaborador.model.util.Constants;
import br.com.eucolaborador.model.util.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * Interface de acesso a API de usuário
 */
public class UserDAO extends Dao {
    private static UserToken userToken;

    public UserDAO(Context context) {
        super(context, Usuario.class, "/Usuario/");
    }

    /***
     * Login de usuário
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast para UserToken.
     * @param login login do usuário
     * @param password senha do usuário
     */
    public void login(String login, String password, final ServerCallback serverCallback) {

        final String endPoint = Constants.apiurl + "/usuario/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> jsonToUserToken(response, serverCallback),
                error -> serverCallback.onResponse(false, new String(error.networkResponse.data, StandardCharsets.UTF_8))) {

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("login", login);
                params.put("password", password);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    /***
     * Logout de usuário
     */
    public void logout() {
        userToken = null;
    }

    /***
     * Criação de usuário
     * @param nome
     * @param cpf
     * @param email
     * @param senha
     * @param dataNascimento
     * @param sexo
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast para User.
     */
    public void create(String nome, String cpf, String email, String senha, String confirmacaosenha, Date dataNascimento, char sexo, final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> jsonToUserToken(response, serverCallback),
                error -> serverCallback.onResponse(false, new String(error.networkResponse.data, StandardCharsets.UTF_8))) {

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("nome", nome);
                params.put("cpf", cpf);
                params.put("email", email);
                params.put("password", senha);
                params.put("confirmacaosenha", confirmacaosenha);
                params.put("dataNascimento", sdf.format(dataNascimento));
                params.put("sexo", String.valueOf(sexo));
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void update(String nome, Date dataNascimento, char sexo, final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller;

        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
                endPoint,
                response -> jsonToUserToken(response, serverCallback),
                error -> serverCallback.onResponse(false, null)) {

            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("nome", nome);
                params.put("dataNascimento", sdf.format(dataNascimento));
                params.put("sexo", String.valueOf(sexo));
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void recuperarSenha(String email, final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller + "recuperarsenha";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> serverCallback.onResponse(true, null),
                error -> serverCallback.onResponse(false, null)) {

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", email);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void alterarEmail(String novoEmail, final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller + "AlterarEmail";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> serverCallback.onResponse(true, null),
                error -> serverCallback.onResponse(false, null)) {

            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("NovoEmail", novoEmail);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void trocarSenha(String senhaAntiga, String novaSenha, String confirmacaoSenha, final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller + "alterarsenha";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> serverCallback.onResponse(true, null),
                error -> serverCallback.onResponse(false, null)) {

            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("senhaAntiga", senhaAntiga);
                params.put("novaSenha", novaSenha);
                params.put("confirmacaoSenha", confirmacaoSenha);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void excluirUsuario(final ServerCallback serverCallback) {
        final String endPoint = Constants.apiurl + controller;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
                endPoint,
                response -> serverCallback.onResponse(true, null),
                error -> serverCallback.onResponse(false, null)) {

            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void reenviarEmail(String email, ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + "enviaremailconfirmacao?email=" + email;
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

    /***
     * Retorna usuário logado no sistema
     * @return userToken usuário logado
     */
    public static UserToken getLoggedUser() {
        return userToken;
    }

    public static Map<String, String> getHeaderToken() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + userToken.getToken());
        return headers;
    }

    public boolean isLogged() {
        return userToken != null;
    }

    private void jsonToUserToken(String response, ServerCallback serverCallback) {
        try {
            JSONObject jsonFromResponse = new JSONObject(response);
            JSONObject jsonUser = jsonFromResponse.getJSONObject("usuario");
            String token = jsonFromResponse.getString("token");
            jsonUser.remove("id");
            Usuario user = gson.fromJson(jsonUser.toString(), Usuario.class);
            this.userToken = new UserToken(user, token);

            serverCallback.onResponse(true, userToken);
        } catch (JSONException e) {
            serverCallback.onResponse(false, null);
        }
    }
}
