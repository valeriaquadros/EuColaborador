package br.com.eucolaborador.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import br.com.eucolaborador.MainActivity;
import br.com.eucolaborador.R;
import br.com.eucolaborador.model.util.Constants;
import br.com.eucolaborador.view.bases.BaseActivity;

public class ConfigIPView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ipconfig);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.button_configip)
                .setOnClickListener(v -> config());
    }

    private void config() {
        EditText etIpConfig = findViewById(R.id.ipconfig_field);
        Constants.apiurl = "http://" + etIpConfig.getText().toString() + ":60633/api";
        Constants.weburl = "http://" + etIpConfig.getText().toString() + ":32772";
        finish();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(0, 0);
    }
}
