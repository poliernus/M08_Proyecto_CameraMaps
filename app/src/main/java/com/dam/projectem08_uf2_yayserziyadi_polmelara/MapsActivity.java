package com.dam.projectem08_uf2_yayserziyadi_polmelara;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import android.content.pm.PackageManager;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.dam.projectem08_uf2_yayserziyadi_polmelara.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    public double latitude;
    public double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    latitude = addresses.get(0).getLatitude();
                                    longitude = addresses.get(0).getLongitude();
                                    LatLng current = new LatLng(latitude, longitude);
                                    Toast.makeText(MapsActivity.this, String.valueOf(longitude), Toast.LENGTH_LONG).show();
                                    mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
                                    float zoomLevel = 11.0f; //This goes up to 21
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomLevel));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(MapsActivity.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }

    }
        // OnMapReady se puede utilizar para poner los marcadores con las fotos

        @Override
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;

        }
}