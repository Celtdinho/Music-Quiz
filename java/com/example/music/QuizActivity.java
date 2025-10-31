package com.example.music;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, scoreText;
    RadioGroup optionsGroup;
    RadioButton optionA, optionB, optionC, optionD;
    Button nextButton;
    MediaPlayer mediaPlayer;

    String[] questions = {
            "Apa judul lagu ini?",
            "Apa judul lagu ini?",
            "Siapa penyanyi lagu ini?",
            "Siapa penyanyi lagu ini?",
            "Apa judul lagu ini?",
    };

    String[][] options = {
            {"Looking out for you", "Mrs Magic", "Moonlight on the river", "Blue hair"},
            {"Anti-Hero", "Love Story", "Telephones", "Lovers rock"},
            {"Clairo", "Strawberry guy", "Mac demarco", "Steve Lacy"},
            {"Vacations", "Eye Dress", "Yot Club", "TV Girl"},
            {"kingston", "Where'd all the time go", "Not allowed", "Sailor song"}
    };

    String[] correct = {"Looking out for you", "Telephones", "Mac demarco", "TV Girl", "Where'd all the time go"};

    int[] songs = {R.raw.looking_out_for_you, R.raw.telephones, R.raw.for_the_first_time, R.raw.lovers_rock, R.raw.where_all_the_time_go};

    int pos = 0;
    int score = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.txtQuestion);
        scoreText = findViewById(R.id.txtScore);
        optionsGroup = findViewById(R.id.radioGroupOptions);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        nextButton = findViewById(R.id.btnNext);

        loadQ();
        nextButton.setOnClickListener(v -> checkAnswer());
    }

    void loadQ() {
        if (mediaPlayer != null) mediaPlayer.release();

        if (pos < questions.length) {
            questionText.setText(questions[pos]);
            optionA.setText(options[pos][0]);
            optionB.setText(options[pos][1]);
            optionC.setText(options[pos][2]);
            optionD.setText(options[pos][3]);
            scoreText.setText("Skor: " + score);
            optionsGroup.clearCheck();

            mediaPlayer = MediaPlayer.create(this, songs[pos]);
            mediaPlayer.start();
        } else {
            questionText.setText("Selesai! Skor: " + score);
            optionsGroup.setVisibility(android.view.View.GONE);
            nextButton.setEnabled(false);
        }
    }

    void checkAnswer() {
        int id = optionsGroup.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Pilih dulu!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton pick = findViewById(id);

        // ✅ STOP / PAUSE AUDIO ketika jawab
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

        if (pick.getText().equals(correct[pos])) score += 20;

        pos++;
        loadQ();
    }


    @Override protected void onDestroy() {
        if (mediaPlayer != null) mediaPlayer.release();
        super.onDestroy();
    }
}
