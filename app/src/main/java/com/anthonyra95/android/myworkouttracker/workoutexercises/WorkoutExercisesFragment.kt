package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabase
import com.anthonyra95.android.myworkouttracker.databinding.FragmentWorkoutExercisesBinding


class WorkoutExercisesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentWorkoutExercisesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workout_exercises, container, false
        )

        val application = requireNotNull(this.activity).application

        val arguments = WorkoutExercisesFragmentArgs.fromBundle(requireArguments())
        val dataSource = WorkoutDatabase.getInstance(application).workoutDatabaseDao
        val viewModelFactory = WorkoutExercisesVeiwModelFactory(arguments.exerciseKey,dataSource)

        val workoutExercisesViewModel = ViewModelProvider(this,viewModelFactory).get(WorkoutExercisesViewModel::class.java)
        binding.workoutExercisesViewModel = workoutExercisesViewModel

        workoutExercisesViewModel.navigateToWorkoutTracker.observe(viewLifecycleOwner, Observer {
            if(it==true){
                this.findNavController().navigate(
                    WorkoutExercisesFragmentDirections.actionWorkoutExercisesFragmentToWorkoutTrackerFragment())
                workoutExercisesViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}