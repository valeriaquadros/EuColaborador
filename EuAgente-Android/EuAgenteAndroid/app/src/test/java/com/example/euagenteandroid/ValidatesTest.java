package com.example.euagenteandroid;

import com.example.euagenteandroid.model.util.Converter;
import com.example.euagenteandroid.model.util.Validates;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ValidatesTest {

    @Test
    public void loginComCampoNaoPreenchido() {
        Assert.assertFalse(Validates.login("08928988012", ""));
        Assert.assertFalse(Validates.login("", "123456"));
        Assert.assertFalse(Validates.login("", ""));
        Assert.assertTrue(Validates.login("08928988012", "123456"));
    }

    @Test
    public void criarUsuarioComCampoNaoPreenchido() {
        Assert.assertFalse(Validates.criarUsuario("Patricia", "08928988012", "jorge@email.com", "123456", "123456", null));
        Assert.assertFalse(Validates.criarUsuario("Patricia", "08928988012", "jorge@email.com", "123456", "", 'F'));
        Assert.assertFalse(Validates.criarUsuario("Patricia", "08928988012", "jorge@email.com", "", "123456", 'F'));
        Assert.assertFalse(Validates.criarUsuario("Patricia", "08928988012", "", "123456", "123456", 'F'));
        Assert.assertFalse(Validates.criarUsuario("Patricia", "", "jorge@email.com", "123456", "123456", 'F'));
        Assert.assertFalse(Validates.criarUsuario("", "08928988012", "jorge@email.com", "123456", "123456", 'F'));
        Assert.assertFalse(Validates.criarUsuario("", "", "", "", "", null));
        Assert.assertTrue(Validates.criarUsuario("Patricia", "08928988012", "jorge@email.com", "123456", "123456", 'F'));
    }

    @Test
    public void alterarSenhaComCampoNaoPreenchido() {
        Assert.assertFalse(Validates.alterarSenha("123456", "111111", ""));
        Assert.assertFalse(Validates.alterarSenha("123456", "", "111111"));
        Assert.assertFalse(Validates.alterarSenha("", "111111", "111111"));
        Assert.assertFalse(Validates.alterarSenha("", "", ""));
        Assert.assertTrue(Validates.alterarSenha("123456", "111111", "111111"));
    }

    @Test
    public void alterarSenhaComSenhasNaoCompativeis() {
        Assert.assertFalse(Validates.criarUsuarioSenhas("111111", "123456"));
        Assert.assertFalse(Validates.criarUsuarioSenhas("111111", "11111"));
        Assert.assertTrue(Validates.criarUsuarioSenhas("111111", "111111"));
    }

    @Test
    public void enviarChecklistSemTerSelecionadoItens() {
        Assert.assertFalse(Validates.checkListEndereco(new ArrayList<>()));
    }

    @Test
    public void registrarDenunciaSemEndereco() {
        Assert.assertFalse(Validates.denunciaLocal(null));
    }

    @Test
    public void cadastrarEnderecoComCamposNaoPreenchidos() {
        Assert.assertFalse(Validates.endereco("79071300", "Rua Manoel", "123", "Alves", "Campo Grande", ""));
        Assert.assertFalse(Validates.endereco("79071300", "Rua Manoel", "123", "Alves", "", "Mato Grosso do Sul"));
        Assert.assertFalse(Validates.endereco("79071300", "Rua Manoel", "123", "", "Campo Grande", "Mato Grosso do Sul"));
        Assert.assertFalse(Validates.endereco("79071300", "Rua Manoel", "", "Alves", "Campo Grande", "Mato Grosso do Sul"));
        Assert.assertFalse(Validates.endereco("79071300", "", "123", "Alves", "Campo Grande", "Mato Grosso do Sul"));
        Assert.assertFalse(Validates.endereco("", "Rua Manoel", "123", "Alves", "Campo Grande", "Mato Grosso do Sul"));
        Assert.assertFalse(Validates.endereco("", "", "", "", "", ""));
        Assert.assertTrue(Validates.endereco("79071300", "Rua Manoel", "123", "Alves", "Campo Grande", "Mato Grosso do Sul"));
    }

    @Test
    public void editarPerfilComCamposNaoPreenchidos() {
        Assert.assertFalse(Validates.editarPerfil("Aragao", null));
        Assert.assertFalse(Validates.editarPerfil("", Converter.getDate("21/12/1994")));
        Assert.assertFalse(Validates.editarPerfil("", null));
        Assert.assertTrue(Validates.editarPerfil("Aragao", Converter.getDate("21/12/1994")));
    }

    @Test
    public void criarPessoaComCamposNaoPreenchidos() {
        Assert.assertFalse(Validates.pessoa("Renato", "08928988012", null));
        Assert.assertFalse(Validates.pessoa("Renato", "", 'M'));
        Assert.assertFalse(Validates.pessoa("", "08928988012", 'M'));
        Assert.assertFalse(Validates.pessoa("", "", null));
        Assert.assertTrue(Validates.pessoa("Renato", "08928988012", 'M'));
    }
}
