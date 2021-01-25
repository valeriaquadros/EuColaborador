package com.example.euagenteandroid.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.entity.Endereco;
import com.example.euagenteandroid.model.util.Constants;
import com.example.euagenteandroid.model.util.Validates;
import com.example.euagenteandroid.view.bases.BaseActivityWithLocation;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class DenunciaLocalView extends BaseActivityWithLocation {
    private EditText etEdereco;
    protected Button btnEnderecoGps;
    private LinearLayout llEndereco;
    private ImageView ivLocal;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_denuncia_local);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etEdereco = findViewById(R.id.denuncia_local_et_endereco);
        llEndereco = findViewById(R.id.denuncia_local_ll_endereco);
        btnEnderecoGps = findViewById(R.id.denuncia_local_btn_endereco_gps);
        ivLocal = findViewById(R.id.denuncia_local_iv);

        findViewById(R.id.denuncia_local_btn_endereco_informar).setOnClickListener(v -> cadastrarEndereco());
        btnEnderecoGps.setOnClickListener(v -> buscarEndereco());

        findViewById(R.id.denuncia_local_btn_editar_endereco).setOnClickListener(v -> editarEndereco());

        findViewById(R.id.denuncia_local_btn).setOnClickListener(v -> enviar());
    }

    private void cadastrarEndereco() {
        ivLocal.setVisibility(View.GONE);
        startActivityForResult(new Intent(this, EnderecoView.class), 2);
    }

    private void buscarEndereco() {
        findViewById(R.id.lav_thumbDown).setVisibility(View.VISIBLE);
        initEnderecoGps();
    }

    @Override
    protected void callBackGps() {
        if (fusedLocationClient != null)
            fusedLocationClient.removeLocationUpdates(locationCallback);
        buscarBairroCep();
    }

    private void buscarBairroCep() {
        getDaoFacade().getEnderecoDAO().getEnderecoByCep(endereco.getCep(), (success, response) -> {
            if (success) {
                Endereco enderecoResponse = (Endereco) response;
                if (!enderecoResponse.getLogradouro().isEmpty())
                    endereco.setLogradouro(enderecoResponse.getLogradouro());
                if (!enderecoResponse.getBairro().isEmpty())
                    endereco.setBairro(enderecoResponse.getBairro());
                if (!enderecoResponse.getCidade().isEmpty())
                    endereco.setCidade(enderecoResponse.getCidade());
                if (!enderecoResponse.getEstado().isEmpty())
                    endereco.setEstado(enderecoResponse.getEstado());
                setEnderecoNoLayout();
            } else {
                makeText(DenunciaLocalView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            setEnderecoNoLayout();
        }
    }

    private void setEnderecoNoLayout(){
        etEdereco.post(() -> {
            etEdereco.setText(endereco.getLogradouro() + ", " + endereco.getNumero());
            findViewById(R.id.lav_thumbDown).setVisibility(View.GONE);
            llEndereco.setVisibility(View.VISIBLE);
            baixarImagemClick();
        });
    }

    public void enviar() {
        if (Validates.denunciaLocal(endereco)) {
            super.getDaoFacade().getEnderecoDAO()
                    .create(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getLatitude(), endereco.getLongitude(), (success, response) -> {
                        if (success) {
                            endereco = (Endereco) response;
                            cadastrarDenuncia();
                        } else {
                            makeText(DenunciaLocalView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
                        }
                    });
        } else
            makeText(this, R.string.toast_login_empty, LENGTH_SHORT).show();
    }

    private void cadastrarDenuncia() {
        super.getDaoFacade().getDenunciaFocoDAO().create(endereco.getId(), 1, (success, response) -> {
            if (success) {
                showSucesso(this, R.string.denuncia_local_sucesso_titulo);
                finish();
            } else {
                makeText(DenunciaLocalView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
            }
        });
    }

    private void editarEndereco() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("editar_endereco", endereco);
        startActivityForResult(new Intent(this, EnderecoView.class).putExtras(bundle), 2);
        ivLocal.setVisibility(View.GONE);
    }

    public void baixarImagemClick() {
        ivLocal.setVisibility(View.VISIBLE);
        new Thread() {
            public void run(){
                Bitmap img = null;
                try {
                    URL url = new URL("https://dev.virtualearth.net/REST/V1/Imagery/Map/Road/"
                            +endereco.getLatitude()+"%2C"+endereco.getLongitude()
                            +"/18?mapSize=300,300&format=png&pushpin="+endereco.getLatitude()+","
                            +endereco.getLongitude()+";64;Hi&key="+Constants.bingapikey);
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                    InputStream input = conexao.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                } catch (Exception e) { }

                Bitmap finalImg = img;
                handler.post(() -> ivLocal.setImageBitmap(finalImg));
            }
        }.start();

    }
}
