package com.example.euagenteandroid;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.euagenteandroid.model.util.Constants;
import com.example.euagenteandroid.view.ConfigIPView;
import com.example.euagenteandroid.view.bases.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addItemTutorial(R.id.navigation_home, R.string.tutorial_home, "intro_home");
        addItemTutorial(R.id.navigation_infos, R.string.tutorial_informacoes, "intro_informacoes");
        addItemTutorial(R.id.navigation_prevencao, R.string.tutorial_prevencao, "intro_prevencao");
        addItemTutorial(R.id.navigation_map, R.string.tutorial_mapa,"intro_mapa");
        addItemTutorial(R.id.expanded_menu, R.string.tutorial_menu, "intro_menu");
        showTutorial();

        if (Constants.apiurl == null)
            super.goTo(ConfigIPView.class);
    }
}
