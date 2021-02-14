package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.util.Validates;
import br.com.eucolaborador.view.bases.BaseActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class AlterarSenhaView extends BaseActivity {

    private EditText etSenhaAntiga;
    private EditText etNovaSenha;
    private EditText etNovaSenhaConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_alterar_senha);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etSenhaAntiga = findViewById(R.id.alterar_senha_antiga);
        etNovaSenha = findViewById(R.id.alterar_senha_nova);
        etNovaSenhaConfirmacao = findViewById(R.id.alterar_senha_nova_confirmacao);

        findViewById(R.id.alterar_senha_btn_salvar).setOnClickListener(v -> enviar());
    }

    private void enviar(){
        String senhaAntiga = etSenhaAntiga.getText().toString();
        String novaSenha = etNovaSenha.getText().toString();
        String novaSenhaConfirmacao = etNovaSenhaConfirmacao.getText().toString();

        if(Validates.alterarSenha(senhaAntiga, novaSenha, novaSenhaConfirmacao)) {
            getDaoFacade().getUserDAO().trocarSenha(senhaAntiga, novaSenha, novaSenhaConfirmacao, (success, response) -> {
                if(success){
                    showSucesso(this, R.string.alterar_senha_sucesso_titulo);
                } else {
                    makeText(AlterarSenhaView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
                }
            });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }
}
