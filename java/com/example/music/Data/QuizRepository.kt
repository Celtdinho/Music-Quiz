package com.example.music.data

import android.content.Context
import org.json.JSONArray

class QuizRepository(private val context: Context) {

    fun loadLevel(level: Int): List<QuizModel> {
        val fileName = when(level) {
            1 -> "soal_level_1"
            2 -> "soal_level_2"
            else -> "soal_level_1"
        }

        val inputStream = context.resources.openRawResource(
            context.resources.getIdentifier(fileName, "raw", context.packageName)
        )
        val json = inputStream.bufferedReader().use { it.readText() }

        val array = JSONArray(json)
        val quizList = mutableListOf<QuizModel>()

        for(i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            quizList.add(
                QuizModel(
                    id = obj.getInt("id"),
                    question = obj.getString("question"),
                    options = listOf(
                        obj.getString("a"),
                        obj.getString("b"),
                        obj.getString("c"),
                        obj.getString("d")
                    ),
                    answerIndex = obj.getInt("answer"),
                    audio = obj.getString("audio")
                )
            )
        }
        return quizList
    }
}
