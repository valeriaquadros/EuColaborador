package br.com.eucolaborador.model.util;

import br.com.eucolaborador.model.entity.Endereco;

import java.util.ArrayList;
import java.util.Date;

public class Validates {

    public static boolean login(String login, String senha) {
        return !(login.isEmpty() || senha.isEmpty());
    }

    public static boolean criarUsuario(String nome, String cpf, String email, String senha1, String senha2, Character sexo) {
        return !(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha1.isEmpty() || senha2.isEmpty() || sexo == null);
    }

    public static boolean criarUsuarioSenhas(String senha, String senhaConfirmacao) {
        return senha.equals(senhaConfirmacao);
    }

    public static boolean alterarSenha(String senhaAntiga, String novaSenha, String novaSenhaConfirmacao) {
        return !(senhaAntiga.isEmpty() || novaSenha.isEmpty() || novaSenhaConfirmacao.isEmpty());
    }

    public static boolean checkListEndereco(ArrayList<Integer> listFocosIds) {
        return !listFocosIds.isEmpty();
    }

    public static boolean denunciaLocal(Endereco endereco) {
        return endereco != null;
    }

    public static boolean endereco(String cep, String logradouro, String numero, String bairro, String cidade, String estado) {
        return !(cep.isEmpty() || logradouro.isEmpty() || numero.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty());
    }

    public static boolean editarPerfil(String nome, Date dataNascimento) {
        return !(nome.isEmpty() || dataNascimento == null);
    }

    public static boolean pessoa(String nome, String cpf, Character sexo) {
        return !(nome.isEmpty() || cpf.isEmpty() || sexo == null);
    }
}
