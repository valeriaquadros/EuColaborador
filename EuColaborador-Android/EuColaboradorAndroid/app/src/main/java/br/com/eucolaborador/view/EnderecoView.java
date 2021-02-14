package br.com.eucolaborador.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.entity.Endereco;
import br.com.eucolaborador.model.util.Validates;
import br.com.eucolaborador.view.bases.BaseActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EnderecoView extends BaseActivity {

    private EditText etCep;
    private EditText etLogradouro;
    private EditText etNumero;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etEstado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_endereco);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        configuracaoInicial();

        findViewById(R.id.button_address).setOnClickListener(v -> enviar());

        if(getIntent().getExtras() != null) {
            preencheEnderecoPorGPS((Endereco) getIntent().getExtras().getSerializable("editar_endereco"));
        }
    }

    private void configuracaoInicial() {
        capturarCampos();
        setEnderecoByCep();
    }

    private void capturarCampos() {
        etCep = findViewById(R.id.campo_cep);
        etLogradouro = findViewById(R.id.campo_logradouro);
        etNumero = findViewById(R.id.campo_numero);
        etBairro = findViewById(R.id.campo_bairro);
        etCidade = findViewById(R.id.campo_cidade);
        etEstado = findViewById(R.id.campo_estado);
    }

    private void setEnderecoByCep() {
        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cep = etCep.getText().toString();
                if (cep.length() == 8) {
                    getDaoFacade().getEnderecoDAO().getEnderecoByCep(cep, (success, response) -> {
                        if (success) {
                            Endereco endereco = (Endereco) response;
                            etLogradouro.setText(endereco.getLogradouro());
                            etBairro.setText(endereco.getBairro());
                            etCidade.setText(endereco.getCidade());
                            etEstado.setText(endereco.getEstado());
                        }
                    });
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Sem comportamento específico de classe
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Sem comportamento específico de classe
            }
        });
    }

    private void enviar() {
        String logradouro = etLogradouro.getText().toString();
        String numero = etNumero.getText().toString();
        String cep = etCep.getText().toString();
        String bairro = etBairro.getText().toString();
        String cidade = etCidade.getText().toString();
        String estado = etEstado.getText().toString();

        if (Validates.endereco(cep, logradouro, numero, bairro, cidade, estado)) {
            super.getDaoFacade().getEnderecoDAO().create(logradouro, numero, bairro, cidade, estado, cep, (success, response) -> {
                if (success) {
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("endereco", (Endereco) response);
                    setResult(2, returnIntent);
                    makeText(this, R.string.toast_register_successful, LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    makeText(this, R.string.toast_failed, LENGTH_SHORT).show();
                }
            });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }

    private void preencheEnderecoPorGPS(Endereco endereco) {
        etCep.setText(endereco.getCep());
        etLogradouro.setText(endereco.getLogradouro());
        etNumero.setText(endereco.getNumero());
    }
}
