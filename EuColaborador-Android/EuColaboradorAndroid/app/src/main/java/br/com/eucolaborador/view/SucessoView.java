package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import br.com.eucolaborador.R;
import br.com.eucolaborador.view.bases.BaseActivity;

public class SucessoView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sucesso);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((TextView)findViewById(R.id.sucesso_titulo))
            .setText(getResources().getText(getIntent().getExtras().getInt("titulo")));

        if(getIntent().getExtras().containsKey("subtitulo"))
            ((TextView)findViewById(R.id.sucesso_subtitulo))
                    .setText(getResources().getText(getIntent().getExtras().getInt("subtitulo")));
    }
}
