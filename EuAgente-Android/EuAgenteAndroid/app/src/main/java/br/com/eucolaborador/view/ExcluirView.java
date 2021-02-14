package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.WindowManager;

import br.com.eucolaborador.MainActivity;
import br.com.eucolaborador.R;
import br.com.eucolaborador.view.bases.BaseActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ExcluirView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_excluir);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.excluir_btn).setOnClickListener(v -> enviar());
    }

    private void enviar(){
        getDaoFacade().getUserDAO().excluirUsuario((success, response) -> {
            if(success){
                getDaoFacade().getUserDAO().logout();
                goTo(MainActivity.class);
                showSucesso(this, R.string.excluir_sucesso);
            } else {
                makeText(ExcluirView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
            }
        });
    }
}
