package com.example.euagenteandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.dao.UserDAO;
import com.example.euagenteandroid.model.entity.Usuario;
import com.example.euagenteandroid.model.util.Converter;
import com.example.euagenteandroid.model.util.Validates;
import com.example.euagenteandroid.view.bases.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class PerfilEditView extends BaseActivity {

    private EditText etNome;
    private EditText etDataNascimento;
    private RadioButton rbF;
    private RadioButton rbM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_perfil_edit);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etNome = findViewById(R.id.perfil_et_nome);
        etDataNascimento = findViewById(R.id.perfil_et_data_nascimento);
        rbF = findViewById(R.id.perfil_edit_sexo_f);
        rbM = findViewById(R.id.perfil_edit_sexo_m);

        findViewById(R.id.perfil_edit_salvar).setOnClickListener(v -> enviar());

        buscarUsuario();
    }

    private void buscarUsuario() {
        Usuario usuario = UserDAO.getLoggedUser().getUsuario();
        etNome.setText(usuario.getNome());
        etDataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento()));
        (usuario.getSexo().equals("M") ? rbM : rbF).setChecked(true);
    }

    public void enviar() {
        String nome = etNome.getText().toString();
        Date dataNascimento = Converter.getDate(etDataNascimento.getText().toString());
        char sexo = (rbM.isChecked() ? 'M' : 'F');

        if (Validates.editarPerfil(nome, dataNascimento)) {
            super.getDaoFacade().getUserDAO().update(nome, dataNascimento, sexo, (success, response) -> {
                if (success) {
                    showSucesso(this, R.string.perfil_sucesso);
                    finish();
                } else {
                    makeText(PerfilEditView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
                }
            });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }
}
