package com.example.music;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 100;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base, LocaleHelper.getLanguage(base)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fade);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);

        } else setLanguageByLocation();
    }

    private void goMain() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 2000);
    }

    private void setLanguageByLocation() {
        try {
            LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
            Location loc = null;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (loc != null) {
                Geocoder geo = new Geocoder(this, Locale.getDefault());
                List<Address> a = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                String province = a.get(0).getAdminArea();

                if (province.contains("Jawa")) LocaleHelper.setLocale(this, "jv");
                else if (province.contains("Bandung") || province.contains("Jawa Barat"))
                    LocaleHelper.setLocale(this, "su");
                else if (province.contains("Sumatera"))
                    LocaleHelper.setLocale(this, "min");
                else LocaleHelper.setLocale(this, "id");
            }
        } catch (Exception ignored){}

        goMain();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] p, int[] r) {
        super.onRequestPermissionsResult(requestCode,p,r);
        if (requestCode == REQUEST_LOCATION) setLanguageByLocation();
    }
}
