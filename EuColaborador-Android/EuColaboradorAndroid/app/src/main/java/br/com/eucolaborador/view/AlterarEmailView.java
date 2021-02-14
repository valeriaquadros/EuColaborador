package br.com.eucolaborador.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import br.com.eucolaborador.R;
import br.com.eucolaborador.view.bases.BaseActivity;

import static android.widget.Toast.makeText;

public class AlterarEmailView extends BaseActivity {

    private EditText etNovoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_alterar_email);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etNovoEmail = findViewById(R.id.alterar_email_et);

        findViewById(R.id.alterar_email_btn_salvar).setOnClickListener(v -> enviar());
    }

    private void enviar(){
        findViewById(R.id.alterar_email_btn_salvar).setVisibility(View.GONE);
        findViewById(R.id.lav_thumbDown).setVisibility(View.VISIBLE);
        String novoEmail = etNovoEmail.getText().toString();

        getDaoFacade().getUserDAO().alterarEmail(novoEmail, ((success, response) -> {
            if (success) {
                showSucessoComSub(this, R.string.alterar_email_sucesso_titulo, R.string.alterar_email_sucesso_subtitulo);
                finish();
            } else {
                makeText(this, R.string.toast_unavailable_service, Toast.LENGTH_SHORT).show();
                findViewById(R.id.alterar_email_btn_salvar).setVisibility(View.VISIBLE);
                findViewById(R.id.lav_thumbDown).setVisibility(View.GONE);
            }
        }));
    }

}
