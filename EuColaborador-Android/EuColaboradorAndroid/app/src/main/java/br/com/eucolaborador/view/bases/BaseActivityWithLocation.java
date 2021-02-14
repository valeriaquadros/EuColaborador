package br.com.eucolaborador.view.bases;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import br.com.eucolaborador.R;
import br.com.eucolaborador.model.entity.Endereco;
import br.com.eucolaborador.view.DenunciaLocalView;
import br.com.eucolaborador.view.util.FetchAddressIntentService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public abstract class BaseActivityWithLocation extends BaseActivity {
    protected FusedLocationProviderClient fusedLocationClient;
    protected static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    protected LocationAddressResultReceiver addressResultReceiver;
    protected Location currentLocation;
    protected LocationCallback locationCallback;
    protected Endereco endereco = new Endereco();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initEnderecoGps() {
        if (!isConnected()) {
            Toast.makeText(BaseActivityWithLocation.this, R.string.toast_sem_conexao, Toast.LENGTH_LONG).show();
        }

        addressResultReceiver = new LocationAddressResultReceiver(new Handler());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            }
        };

        startLocationUpdates();
    }

    protected boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(DenunciaLocalView.CONNECTIVITY_SERVICE);

        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }

        return false;
    }

    protected void getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(this, R.string.toast_unavailable_service, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            this.endereco = (Endereco) data.getSerializableExtra("endereco");
        }
    }

    protected void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setNumUpdates(1);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {
                    Toast.makeText(this, R.string.toast_unavailable_service,
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    protected class LocationAddressResultReceiver extends ResultReceiver {
        public LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0)
                getAddress();
            else if (resultCode == 1)
                Toast.makeText(BaseActivityWithLocation.this, R.string.toast_unavailable_service, Toast.LENGTH_SHORT).show();
            else if (resultCode == 2) {
                endereco = (Endereco) resultData.getSerializable("address_result");
                callBackGps();
            }
        }
    }

    protected abstract void callBackGps();

    @Override
    protected void onPause() {
        super.onPause();
        if (fusedLocationClient != null)
            fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
