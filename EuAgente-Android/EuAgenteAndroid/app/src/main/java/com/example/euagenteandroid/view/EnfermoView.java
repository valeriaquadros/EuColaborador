package com.example.euagenteandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.dao.UserDAO;
import com.example.euagenteandroid.model.entity.Endereco;
import com.example.euagenteandroid.model.entity.Pessoa;
import com.example.euagenteandroid.view.bases.BaseActivityWithCpfEditText;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EnfermoView extends BaseActivityWithCpfEditText {

    private Switch swGestante;
    private View compPessoa;
    private TextView compNomePessoa;
    private int numeroEnderecosCadastrados;
    private Button btEndereco;
    private Button btPessoa;
    private Button btFinalizar;
    private LinearLayout compEndereco;
    private Pessoa pessoa;
    private List<Endereco> enderecos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_enfermo);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        capturarCampos();

        addItemTutorial(R.id.comp_cpf, R.string.enfermo_tutorial_cpf, "intro_enfermo_cpf");
        showTutorial();
    }

    private void capturarCampos() {
        enderecos = new ArrayList<>(2);
        compPessoa = findViewById(R.id.compPerson);
        compNomePessoa = findViewById(R.id.compPersonName);
        swGestante = findViewById(R.id.sickperson_gestante);
        btEndereco = findViewById(R.id.sickperson_enderecoButton);
        btPessoa = findViewById(R.id.sickperson_pessoaButton);
        btFinalizar = findViewById(R.id.sickperson_button);
        compEndereco = findViewById(R.id.compAddress);
    }

    public void cadastrar(View view) {
        getDaoFacade().getEnfermoDAO().create(swGestante.isChecked(), 1, pessoa.getId(), enderecos, (success, response) -> {
            if (success) {
                showSucesso(this, R.string.cadastro_completo);
                finish();
            } else {
                makeText(this, R.string.toast_failed, LENGTH_SHORT).show();
            }
        });
    }

    public void cadastrarPessoa(View view) {
        Intent intent = new Intent(this, PessoaView.class);
        intent.putExtra("cpf", etCpf.getCleanText());
        startActivityForResult(intent, 3);
    }

    public void cadastrarEndereco(View view) {
        startActivityForResult(new Intent(this, EnderecoView.class), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 2:
                Endereco endereco = (Endereco) data.getSerializableExtra("endereco");
                enderecos.add(endereco);
                inserirComponenteEndereco(endereco);
                break;
            case 3:
                this.pessoa = (Pessoa) data.getSerializableExtra("pessoa");
                inserirComponentePessoa();
                break;
            default:
                break;
        }
    }

    private void inserirComponenteEndereco(Endereco endereco) {
        View tempCompEndereco = View.inflate(this, R.layout.comp_endereco, null);
        tempCompEndereco.findViewById(R.id.btDeletar).setOnClickListener(view1 -> {
            compEndereco.removeView(tempCompEndereco);
            numeroEnderecosCadastrados--;
            enderecos.remove(endereco);
            verificarBotoes();
        });
        compEndereco.addView(tempCompEndereco);
        numeroEnderecosCadastrados++;

        ((TextView) tempCompEndereco.findViewById(R.id.compLogradouro)).setText(endereco.getLogradouro());
        ((TextView) tempCompEndereco.findViewById(R.id.compNumero)).setText(endereco.getNumero());
        ((TextView) tempCompEndereco.findViewById(R.id.compBairro)).setText(endereco.getBairro());

        verificarBotoes();
    }

    private void verificarBotoes() {
        btEnderecoVisible();
        btFinalizarVisible();
    }

    private void btEnderecoVisible() {
        if (numeroEnderecosCadastrados < 2) {
            btEndereco.setVisibility(View.VISIBLE);
        } else {
            btEndereco.setVisibility(View.GONE);
        }
    }

    private void btFinalizarVisible() {
        if (numeroEnderecosCadastrados == 0) {
            btFinalizar.setVisibility(View.GONE);
        } else {
            btFinalizar.setVisibility(View.VISIBLE);
        }
    }

    private void inserirComponentePessoa() {
        compNomePessoa.setText(pessoa.getNome());
        compPessoa.setVisibility(View.VISIBLE);
        btEndereco.setVisibility(View.VISIBLE);
        btPessoa.setVisibility(View.GONE);
        if (pessoa.getSexo().equalsIgnoreCase("f"))
            swGestante.setVisibility(View.VISIBLE);
        else
            swGestante.setVisibility(View.GONE);

        addItemTutorial(R.id.sickperson_enderecoButton, R.string.enfermo_tutorial_endereco, "intro_enfermo_endereco");
        showTutorial();
    }

    public void cpfValido(String cpf) {
        if (cpf.equals(UserDAO.getLoggedUser().getUsuario().getCpf())) {
            getDaoFacade().getPessoaDAO().getPessoaByCpf(cpf, (success, response) -> {
                if (success) {
                    pessoa = (Pessoa) response;
                    inserirComponentePessoa();
                } else
                    exibirBotaoCadastrarPessoa(View.VISIBLE);
            });
        } else
            exibirBotaoCadastrarPessoa(View.VISIBLE);
    }

    @Override
    public void cpfInvalido(String cpf) {
        exibirBotaoCadastrarPessoa(View.GONE);
    }

    private void exibirBotaoCadastrarPessoa(int visible) {
        btPessoa.setVisibility(visible);
        compPessoa.setVisibility(View.GONE);
        swGestante.setVisibility(View.GONE);
        btEndereco.setVisibility(View.GONE);
        btFinalizar.setVisibility(View.GONE);
    }
}
