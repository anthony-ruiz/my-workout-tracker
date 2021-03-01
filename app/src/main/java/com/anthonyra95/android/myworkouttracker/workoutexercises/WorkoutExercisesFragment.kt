package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.databinding.FragmentWorkoutExercisesBinding


class WorkoutExercisesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentWorkoutExercisesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workout_exercises, container, false
        )

        val application = requireNotNull(this.activity).application

        return binding.root
    }
}