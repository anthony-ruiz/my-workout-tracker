package com.anthonyra95.android.myworkouttracker.workoutracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabase
import com.anthonyra95.android.myworkouttracker.databinding.FragmentWorkoutTrackerBinding

class WorkoutTrackerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentWorkoutTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workout_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WorkoutDatabase.getInstance(application).workoutDatabaseDao
        val viewModelFactory = WorkoutTrackerViewModelFactory(dataSource, application)

        val workoutTrackerViewModel = ViewModelProvider(this,viewModelFactory).get(WorkoutTrackerViewModel::class.java)

        binding.workoutTrackerViewModel = workoutTrackerViewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }
}