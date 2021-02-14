package br.com.eucolaborador.view;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.util.Constants;
import br.com.eucolaborador.view.bases.BaseActivityWithLocation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapaView extends BaseActivityWithLocation {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mapa);
        ((BottomNavigationView) findViewById(R.id.bottomNavigationView)).getMenu().getItem(3).setChecked(true);
        super.onCreate(savedInstanceState);

        findViewById(R.id.lav_thumbDown).setVisibility(View.VISIBLE);
        initEnderecoGps();
    }

    @Override
    protected void callBackGps() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
        carregarMapa();
    }

    private void carregarMapa() {
        myWebView = findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        myWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    findViewById(R.id.lav_thumbDown).setVisibility(View.GONE);
                }
            }
        });
        myWebView.loadUrl(Constants.weburl + "/Mapa/Index?latitude=" + endereco.getLatitude() + "&longitude=" + endereco.getLongitude());

    }
}
