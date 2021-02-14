package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioGroup;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.util.Converter;
import br.com.eucolaborador.model.util.Validates;
import br.com.eucolaborador.view.bases.BaseActivityWithCpfEditText;

import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class CadastroUsuarioView extends BaseActivityWithCpfEditText {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha1;
    private EditText etSenha2;
    private EditText etDataNascimento;
    private RadioGroup etSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cadastrar_usuario);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.button_register)
                .setOnClickListener(v -> cadastrar());

        findViewById(R.id.goToLogin)
                .setOnClickListener(v -> goToLogin());
    }

    private void cadastrar() {
        capturarCampos();
        enviar();
    }

    private void capturarCampos() {
        etNome = findViewById(R.id.register_field_name);
        etEmail = findViewById(R.id.register_field_email);
        etDataNascimento = findViewById(R.id.register_field_birth_date);
        etSexo = findViewById(R.id.register_field_sex);
        etSenha1 = findViewById(R.id.register_field_password_1);
        etSenha2 = findViewById(R.id.register_field_password_2);
    }

    public void enviar() {
        String nome = etNome.getText().toString();
        String cpf = etCpf.getCleanText();
        String email = etEmail.getText().toString();
        Date dataNascimento;
        try {
            dataNascimento = Converter.getDate(etDataNascimento.getText().toString());
        } catch (Exception e) {
            makeText(this, R.string.toast_invalid_date, LENGTH_SHORT).show();
            return;
        }
        Character sexo = getSexo();
        String senha1 = etSenha1.getText().toString();
        String senha2 = etSenha2.getText().toString();

        if (validar(nome, cpf, email, senha1, senha2, sexo)) {
            super.getDaoFacade().getUserDAO().create(nome, cpf, email, senha1, senha2, dataNascimento, sexo, (success, response) -> {
                if (success) {
                    makeText(this, R.string.toast_register_successful, LENGTH_SHORT).show();
                    finish();
                } else {
                    makeText(this, R.string.toast_failed, LENGTH_SHORT).show();
                    makeText(this, response.toString(), LENGTH_SHORT).show();
                    etSenha1.getText().clear();
                    etSenha2.getText().clear();
                }
            });
        }
    }

    private Character getSexo() {
        switch (etSexo.getCheckedRadioButtonId()) {
            case R.id.register_field_sex_f:
                return 'F';
            case R.id.register_field_sex_m:
                return 'M';
            default:
                return null;
        }
    }

    private boolean validar(String nome, String cpf, String email, String senha1, String senha2, Character sexo) {
        if (senha1.length() < 6) {
            makeText(this, "A senha deve possuir no mínimo 6 caracteres", LENGTH_SHORT).show();
            return false;
        }
        if (!Validates.criarUsuario(nome, cpf, email, senha1, senha2, sexo)) {
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
            return false;
        }
        if (!Validates.criarUsuarioSenhas(senha1, senha2)){
            makeText(this, R.string.toast_register_error_passwords, LENGTH_SHORT).show();
            etSenha1.getText().clear();
            etSenha2.getText().clear();
            return false;
        }
        return true;
    }

    public void goToLogin() {
        finish();
        super.goTo(LoginView.class);
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
