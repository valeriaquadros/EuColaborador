package com.example.euagenteandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.euagenteandroid.MainActivity;
import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.util.Constants;
import com.example.euagenteandroid.view.bases.BaseActivity;

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
