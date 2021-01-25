package com.example.euagenteandroid.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.entity.Pessoa;
import com.example.euagenteandroid.model.util.Converter;
import com.example.euagenteandroid.model.util.Validates;
import com.example.euagenteandroid.view.bases.BaseActivityWithCpfEditText;

import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class PessoaView extends BaseActivityWithCpfEditText {
    DatePickerDialog picker;
    EditText etNome;
    RadioGroup etSexo;
    EditText etDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pessoa);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.pessoa_button).setOnClickListener(v -> registrar());

        configuracaoInicial();
    }


    private void configuracaoInicial() {
        capturarCampos();
        setCpfText();
    }

    private void capturarCampos() {
        etNome = findViewById(R.id.pessoa_field_nome);
        etDataNascimento = findViewById(R.id.pessoa_field_dataNascimento);
        etSexo = findViewById(R.id.pessoa_field_sexo);
    }

    private void setCpfText() {
        etCpf.setText(getIntent().getStringExtra("cpf"));
    }

    public void registrar() {
        String nome = etNome.getText().toString();
        String cpf = etCpf.getCleanText();
        Date dataNascimento;
        try {
            dataNascimento = Converter.getDate(etDataNascimento.getText().toString());
        } catch (Exception e) {
            makeText(this, R.string.toast_invalid_date, LENGTH_SHORT).show();
            return;
        }
        Character sexo = getSexo();

        if (Validates.pessoa(nome, cpf, sexo)) {
            super.getDaoFacade().getPessoaDAO().create(nome, cpf, dataNascimento, sexo, (success, response) -> {
                if (success) {
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("pessoa", (Pessoa) response);
                    setResult(3, returnIntent);
                    makeText(this, R.string.toast_register_successful, LENGTH_SHORT).show();
                    finish();
                } else {
                    makeText(this, R.string.toast_failed, LENGTH_SHORT).show();
                }
            });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }

    private Character getSexo() {
        switch (etSexo.getCheckedRadioButtonId()) {
            case R.id.pessoa_field_f:
                return 'F';
            case R.id.pessoa_field_m:
                return 'M';
            default:
                return null;
        }
    }

    @Override
    public void cpfValido(String cpf) {
        //Sem comportamento específico da classe
    }

    @Override
    public void cpfInvalido(String cpf) {
        //Sem comportamento específico da classe
    }
}
