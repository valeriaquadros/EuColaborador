package br.com.eucolaborador.view.util;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;

import br.com.eucolaborador.model.entity.Endereco;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class FetchAddressIntentService extends IntentService {

    private static final String IDENTIFIER = "FetchAddressIntentService";
    private ResultReceiver addressResultReceiver;

    public FetchAddressIntentService() {
        super(IDENTIFIER);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null)
            return;

        addressResultReceiver = intent.getParcelableExtra("add_receiver");

        if (addressResultReceiver == null) {
            return;
        }

        Location location = intent.getParcelableExtra("add_location");

        if (location == null) {
            sendResultsToReceiver(0, null);
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (Exception ignored){}

        if (addresses == null || addresses.size()  == 0) {
            sendResultsToReceiver(1, null);
        } else {
            Address address = addresses.get(0);

            Endereco endereco = new Endereco();
            endereco.setNumero(address.getFeatureName());
            endereco.setLogradouro(address.getThoroughfare());
            endereco.setCep(address.getPostalCode().replace("-", ""));
            endereco.setBairro(address.getSubLocality());
            endereco.setLatitude(Double.toString(address.getLatitude()));
            endereco.setLongitude(Double.toString(address.getLongitude()));

            sendResultsToReceiver(2, endereco);
        }
    }

    private void sendResultsToReceiver(int resultCode, Object message) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("address_result", (Serializable) message);
        addressResultReceiver.send(resultCode, bundle);
    }
}
