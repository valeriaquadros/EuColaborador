package com.example.euagenteandroid.view.bases;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.util.ValidaCPF;
import com.example.euagenteandroid.view.util.CpfEditText;

public abstract class BaseActivityWithCpfEditText extends BaseActivity {
    protected CpfEditText etCpf;
    private TextView cpfMessage;

    public abstract void cpfValido(String cpf);

    public abstract void cpfInvalido(String cpf);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCpfListener();
    }

    protected void avaliarCpf() {
        String cpf = etCpf.getCleanText();
        if (cpf.length() == 11) {
            if (ValidaCPF.isCPF(cpf)) {
                cpfMessage.setVisibility(View.GONE);
                cpfValido(cpf);
            } else {
                cpfMessage.setVisibility(View.VISIBLE);
            }
        } else {
            cpfMessage.setVisibility(View.GONE);
            cpfInvalido(cpf);
        }
    }

    protected void setCpfListener() {
        etCpf = findViewById(R.id.cpf);
        cpfMessage = findViewById(R.id.cpfMessage);
        etCpf.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                avaliarCpf();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Sem comportamento específico da classe
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Sem comportamento específico da classes
            }
        });
    }
}
