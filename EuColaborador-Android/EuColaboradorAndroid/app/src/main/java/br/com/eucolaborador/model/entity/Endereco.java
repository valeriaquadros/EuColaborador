package br.com.eucolaborador.model.entity;

import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String latitude;
    private String longitude;

    public Endereco() {

    }

    public Endereco(ViaCEPEndereco viaCEPEndereco) {
        logradouro = viaCEPEndereco.getLogradouro();
        cep = viaCEPEndereco.getCep();
        bairro = viaCEPEndereco.getBairro();
        cidade = viaCEPEndereco.getLocalidade();
        estado = viaCEPEndereco.getUf();
    }

    public Endereco(int id, String logradouro, String numero, String bairro, String cidade, String estado, String cep, String latitude, String longitude) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
