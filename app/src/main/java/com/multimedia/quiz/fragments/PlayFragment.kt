package com.multimedia.quiz.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.multimedia.quiz.R
import com.multimedia.quiz.data.listQuestion
import kotlinx.android.synthetic.main.fragment_play.*

class PlayFragment : Fragment(), Runnable {
    var totalQuestion: Int = listQuestion.size
    var currentQuestion: Int = -1
    val navController by lazy { Navigation.findNavController(view!!) }
    val handlerThread = Handler()
    var timerCount = 10
    var timerActive = false

    var questionAnswer = LinkedHashMap<Int, Boolean?>(totalQuestion)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        true_button.setOnClickListener { answer(true) }
        false_button.setOnClickListener { answer(false) }
        nextQuestion()
        startTimer()
    }

    private fun answer(state: Boolean) {
        questionAnswer[currentQuestion] = listQuestion[currentQuestion].answer == state
        nextQuestion()
    }

    fun nextQuestion() {
        if (currentQuestion == totalQuestion) {
            endGame()
            handlerThread.removeCallbacks(this)
            return
        }
        currentQuestion++
        updateQuestion()
        startTimer()
    }

    private fun updateQuestion() {
        if (currentQuestion in 0..(totalQuestion - 1)) {
            val question = listQuestion[currentQuestion]
            question_text.text = question.question
            question_number_text.text = getString(R.string.question_number,
                (currentQuestion+1).toString(),
                totalQuestion.toString())
        } else {
            question_text.text = ""
            endGame()
        }
    }


    fun startTimer(seconds : Int = 10) {
        timerActive = true
        timerCount = seconds
        progress.max = seconds
        handlerThread.removeCallbacks(this)
        progress?.progress = 10 - timerCount
        updateTimer()
    }

    fun updateTimer(){
        timerCount--

        handlerThread.postDelayed(this, 1000)
    }

    override fun run() {
        if (timerCount >= 0 && timerActive){
            progress?.progress = 10 - timerCount
            updateTimer()
        } else if (timerActive) {
            nextQuestion()
        } else {
            Log.d(PlayFragment::class.java.simpleName, "Game ended")
        }
    }

    fun endGame() {
        timerActive = false
        var correct = 0
        var wrong = 0
        for (a in questionAnswer) {
            if (a.value == true) correct++
            if (a.value == false) wrong++
        }
        val bundle = Bundle().apply {
            putInt("correct", correct)
            putInt("wrong", wrong)
        }
        if (view != null) {
            Navigation.findNavController(view!!).navigate(R.id.end_game_action, bundle)
        }
    }
}