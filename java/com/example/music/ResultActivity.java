// File: app/src/main/java/com/example/music/ResultActivity.java
package com.example.music;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResultTitle, tvCorrect, tvWrong, tvScore;
    private Button btnBackMenu, btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultTitle = findViewById(R.id.tvResultTitle);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvScore = findViewById(R.id.tvStage); // reuse id for stage text

        btnBackMenu = findViewById(R.id.btnBackToMenu);
        btnRetry = findViewById(R.id.btnNextStage);

        int correct = getIntent().getIntExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        int total = getIntent().getIntExtra("total", correct + wrong);

        tvCorrect.setText("CORRECT : " + correct);
        tvWrong.setText("WRONG : " + wrong);
        tvScore.setText("SCORE: " + correct + " / " + total);

        btnBackMenu.setOnClickListener(v -> {
            startActivity(new Intent(ResultActivity.this, MainActivity.class));
            finish();
        });

        btnRetry.setOnClickListener(v -> {
            // restart quiz
            startActivity(new Intent(ResultActivity.this, QuizActivity.class));
            finish();
        });
    }
}
