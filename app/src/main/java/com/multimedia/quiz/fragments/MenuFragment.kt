package com.multimedia.quiz.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.multimedia.quiz.R
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A placeholder fragment containing a simple view.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_play)
        }
        exit_button.setOnClickListener {
            Toast.makeText(activity, "Exit application", Toast.LENGTH_SHORT).show()
        }
    }
}
