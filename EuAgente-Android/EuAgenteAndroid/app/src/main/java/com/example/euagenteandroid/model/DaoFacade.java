package com.example.euagenteandroid.model;

import android.content.Context;

import com.example.euagenteandroid.model.dao.ChecklistEnderecoDAO;
import com.example.euagenteandroid.model.dao.DenunciaFocoDAO;
import com.example.euagenteandroid.model.dao.EndemiaDAO;
import com.example.euagenteandroid.model.dao.EnderecoDAO;
import com.example.euagenteandroid.model.dao.EnfermoDAO;
import com.example.euagenteandroid.model.dao.PessoaDAO;
import com.example.euagenteandroid.model.dao.UserDAO;

public class DaoFacade {
    private static DaoFacade daoFacade;
    private EnderecoDAO enderecoDAO;
    private PessoaDAO pessoaDAO;
    private UserDAO userDAO;
    private ChecklistEnderecoDAO checklistEnderecoDAO;
    private EndemiaDAO endemiaDAO;
    private EnfermoDAO enfermoDAO;
    private DenunciaFocoDAO denunciaFocoDAO;

    private DaoFacade(Context context) {
        this.enderecoDAO = new EnderecoDAO(context);
        this.pessoaDAO = new PessoaDAO(context);
        this.userDAO = new UserDAO(context);
        this.checklistEnderecoDAO = new ChecklistEnderecoDAO(context);
        this.endemiaDAO = new EndemiaDAO(context);
        this.enfermoDAO = new EnfermoDAO(context);
        this.denunciaFocoDAO = new DenunciaFocoDAO(context);
    }

    public static DaoFacade getInstance(Context context) {
        if (daoFacade == null) {
            daoFacade = new DaoFacade(context);
        }
        return daoFacade;
    }

    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ChecklistEnderecoDAO getChecklistEnderecoDAO() {
        return checklistEnderecoDAO;
    }

    public EndemiaDAO getEndemiaDAO() {
        return endemiaDAO;
    }

    public EnfermoDAO getEnfermoDAO() {
        return enfermoDAO;
    }

    public DenunciaFocoDAO getDenunciaFocoDAO() {
        return denunciaFocoDAO;
    }
}
