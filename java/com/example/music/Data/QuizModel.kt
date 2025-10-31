package com.example.music.data

data class QuizModel(
    val id: Int,
    val question: String,
    val options: List<String>,
    val answerIndex: Int,
    val audio: String // nama file audio di raw/
)
