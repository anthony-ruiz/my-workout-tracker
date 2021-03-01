package com.anthonyra95.android.myworkouttracker.workoutracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.databinding.FragmentWorkoutTrackerBinding

class WorkoutTrackerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentWorkoutTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workout_tracker, container, false)

        return binding.root
    }
}