package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        findViewById(R.id.btnID).setOnClickListener(v -> changeLang("id"));
        findViewById(R.id.btnJV).setOnClickListener(v -> changeLang("jv"));
        findViewById(R.id.btnSU).setOnClickListener(v -> changeLang("su"));
    }

    private void changeLang(String lang) {
        LocaleHelper.setLocale(this, lang);
        recreate(); // refresh UI
    }
}
