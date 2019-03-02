package com.multimedia.quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.multimedia.quiz.R
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        correct_point_text.text = arguments?.getInt("correct", 0).toString()
        wrong_point_text.text = arguments?.getInt("wrong", 0).toString()
        try_again_button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_try_again)
        }
    }
}