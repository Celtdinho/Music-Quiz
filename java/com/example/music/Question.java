package com.yourapp.musicquiz;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String question;
    private List<String> options;
    private int correctIndex; // 0..3

    public Question(String question, List<String> options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
}
