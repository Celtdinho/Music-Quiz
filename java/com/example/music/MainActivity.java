package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = findViewById(R.id.btn_main);
        Button exit = findViewById(R.id.btn_exit);

        play.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        exit.setOnClickListener(v -> finishAffinity());
    }
}
