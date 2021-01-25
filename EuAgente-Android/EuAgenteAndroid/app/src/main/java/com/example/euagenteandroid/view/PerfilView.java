package com.example.euagenteandroid.view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.dao.UserDAO;
import com.example.euagenteandroid.model.entity.Usuario;
import com.example.euagenteandroid.model.util.ValidaCPF;
import com.example.euagenteandroid.view.bases.BaseActivity;

import java.text.SimpleDateFormat;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class PerfilView extends BaseActivity {

    private TextView tvNome;
    private TextView tvCpf;
    private TextView tvDataNascimento;
    private TextView tvSexo;
    private TextView tvEmail;
    private Button btnConfirmarEmail;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvNome = findViewById(R.id.perfil_tv_nome);
        tvCpf = findViewById(R.id.perfil_tv_cpf);
        tvDataNascimento = findViewById(R.id.perfil_tv_data_nascimento);
        tvSexo = findViewById(R.id.perfil_tv_sexo);
        tvEmail = findViewById(R.id.perfil_tv_email);

        btnConfirmarEmail = findViewById(R.id.perfil_btn_comfirmar_email);

        findViewById(R.id.perfil_btn_editar).setOnClickListener(v -> goTo(PerfilEditView.class));
        findViewById(R.id.perfil_btn_comfirmar_email).setOnClickListener(v -> reenviarEmail());
        findViewById(R.id.perfil_btn_email).setOnClickListener(v -> goTo(AlterarEmailView.class));
        findViewById(R.id.perfil_btn_alterar_senha).setOnClickListener(v -> goTo(AlterarSenhaView.class));
        findViewById(R.id.perfil_btn_excluir).setOnClickListener(v -> goTo(ExcluirView.class));
        btnConfirmarEmail.setOnClickListener(v -> reenviarEmail());

        buscarUsuario();
    }

    private void buscarUsuario() {
        usuario = UserDAO.getLoggedUser().getUsuario();
        tvNome.setText(usuario.getNome());
        tvCpf.setText(ValidaCPF.imprimeCPF(usuario.getCpf()));
        tvDataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento()));
        tvSexo.setText(getResources().getText(usuario.getSexo().equals("M") ? R.string.pessoa_field_m : R.string.pessoa_field_f));
        tvEmail.setText(usuario.getEmail());
        btnConfirmarEmail.setVisibility(usuario.isEmailConfirmado() ? View.GONE : View.VISIBLE);
    }

    private void reenviarEmail() {
        getDaoFacade().getUserDAO().reenviarEmail(usuario.getEmail(), (success, response) -> {
            if (success) {
                showSucesso(this, R.string.perfil_reenviar_email_sucesso);
                btnConfirmarEmail.setVisibility(View.GONE);
            } else {
                makeText(PerfilView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
            }
        });
    }
}
