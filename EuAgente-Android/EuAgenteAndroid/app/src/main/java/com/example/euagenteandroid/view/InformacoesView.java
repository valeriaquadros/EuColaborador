package com.example.euagenteandroid.view;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.view.bases.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InformacoesView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_informacoes);
        ((BottomNavigationView) findViewById(R.id.bottomNavigationView)).getMenu().getItem(1).setChecked(true);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
