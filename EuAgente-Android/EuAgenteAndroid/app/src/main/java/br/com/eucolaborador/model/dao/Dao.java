package br.com.eucolaborador.model.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import br.com.eucolaborador.model.util.Constants;
import br.com.eucolaborador.model.util.ServerCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Dao {
    protected Gson gson;
    protected Context context;
    protected String controller;
    protected Class requestClass;
    protected SimpleDateFormat sdf;

    public Dao(Context context, Class requestClass, String controller) {
        this.context = context;
        this.requestClass = requestClass;
        this.controller = controller;

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.gson = builder.create();
    }

    /**
     * Método utilizado pelo DAO de classe para criar. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param params parametros de entrada
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    protected void create(HashMap<String, Object> params, final ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
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

            @Override
            public byte[] getBody() {
                String jsonObject = new JSONObject(params).toString();
                jsonObject = jsonObject.replace("\"[", "[").replace("]\"", "]").replace("\\", "");
                return jsonObject.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Método utilizado pelo DAO de classe para atualizar. Retorno via serverCallBack.
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id do objeto a ser atualizado
     * @param params parametros de entrada
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    protected void update(int id, HashMap<String, Object> params, final ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + id;
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
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

            @Override
            public byte[] getBody() {
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Retorna objeto pelo id informado através de serverCallBack
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id do objeto
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void getById(int id, ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + id;

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

    /**
     * Retorna lista de objeto cadastrados através de serverCallBack
     * Retorna lista caso sucesso
     * Retorna falha caso falha
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void getList(ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller;
        StringRequest stringRequest = getListStringRequest(serverCallback, endPoint);

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Retorna lista de objeto cadastrados através de serverCallBack
     * Retorna lista caso sucesso
     * Retorna falha caso falha
     *
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    protected void getListByFilterId(int id, ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + "Filter/" + id;
        StringRequest stringRequest = getListStringRequest(serverCallback, endPoint);

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    private StringRequest getListStringRequest(ServerCallback serverCallback, String endPoint) {
        return new StringRequest(Request.Method.GET,
                endPoint,
                response -> {
                    try {
                        List<Object> list = new ArrayList<>();
                        JSONArray jsonFromResponse = new JSONArray(response);
                        for (int i = 0; i < jsonFromResponse.length(); i++) {
                            JSONObject object = jsonFromResponse.getJSONObject(i);
                            list.add(gson.fromJson(object.toString(), requestClass));
                        }
                        serverCallback.onResponse(true, list);
                    } catch (JSONException e) {
                        serverCallback.onResponse(false, null);
                    }
                },
                error -> serverCallback.onResponse(false, null)) {
            @Override
            public Map<String, String> getHeaders() {
                return UserDAO.getHeaderToken();
            }
        };
    }

    /**
     * Detela objeto pelo id informado através de serverCallBack
     * Retorna objeto caso sucesso
     * Retorna nulo caso falha
     * @param id id do objeto
     * @param serverCallback interface para retorno da chamada HTTP. Retorna Object via CallBack. Necessário cast.
     */
    public void deleteById(int id, ServerCallback serverCallback) {
        String endPoint = Constants.apiurl + controller + id;

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
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
