package com.multimedia.quiz.data

data class Question(
    val question: String,
    val answer: Boolean
)

val listQuestion = mutableListOf(
    Question("Apakah ada apel berwarna biru?", false),
    Question("Indonesia merupakan jajahan Belanda", true)
)