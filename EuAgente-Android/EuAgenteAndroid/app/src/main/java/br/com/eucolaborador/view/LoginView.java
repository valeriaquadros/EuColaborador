package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.util.Validates;
import br.com.eucolaborador.view.bases.BaseActivityWithCpfEditText;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class LoginView extends BaseActivityWithCpfEditText {

    private EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        configuracaoInicial();
    }

    private void configuracaoInicial() {
        findViewById(R.id.button_login).setOnClickListener(v -> login());
        findViewById(R.id.forgot_password).setOnClickListener(v -> recuperarSenha());
        findViewById(R.id.goToRegister).setOnClickListener(v -> goToCadastroUsuario());
    }

    private void login() {
        capturarCampos();
        enviar();
    }

    private void capturarCampos() {
        etSenha = findViewById(R.id.password_field);
    }

    public void enviar() {
        String login = etCpf.getCleanText();
        String senha = etSenha.getText().toString();

        if (Validates.login(login, senha)) {
            super.getDaoFacade().getUserDAO().login(login, senha, (success, response) -> {
                if (success) {
                    goToNextActivity();
                    finish();
                } else {
                    makeText(LoginView.this, R.string.toast_login_incorrect, LENGTH_SHORT).show();
                    etSenha.getText().clear();
                }
            });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }

    public void recuperarSenha() {
        finish();
        super.goTo(RecuperarSenhaView.class);
    }

    public void goToCadastroUsuario() {
        finish();
        super.goTo(CadastroUsuarioView.class);
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
