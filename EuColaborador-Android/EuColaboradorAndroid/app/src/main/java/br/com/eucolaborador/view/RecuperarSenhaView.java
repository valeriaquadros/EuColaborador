package br.com.eucolaborador.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.eucolaborador.R;
import br.com.eucolaborador.view.bases.BaseActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class RecuperarSenhaView extends BaseActivity {

    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recuperar_senha);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etEmail = findViewById(R.id.recuperarsenha_email);
    }

    public void enviar(View view) {
        findViewById(R.id.button_recuperarsenha).setVisibility(View.GONE);
        findViewById(R.id.lav_thumbDown).setVisibility(View.VISIBLE);
        String email = etEmail.getText().toString();

        super.getDaoFacade().getUserDAO().recuperarSenha(email, (success, response) -> {
            if (success) {
                showSucesso(this, R.string.recuperarsenha_complete);
                finish();
            } else {
                makeText(this, R.string.recuperarsenha_toast_failed, LENGTH_SHORT).show();
                findViewById(R.id.button_recuperarsenha).setVisibility(View.VISIBLE);
                findViewById(R.id.lav_thumbDown).setVisibility(View.GONE);
            }
        });
    }
}
