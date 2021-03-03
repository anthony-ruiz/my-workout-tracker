package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabase
import com.anthonyra95.android.myworkouttracker.databinding.FragmentWorkoutExercisesBinding
import com.anthonyra95.android.myworkouttracker.workoutracker.WorkoutAdapter


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

        //provide the adapter the data
        val workoutExercisesViewModel = ViewModelProvider(this,viewModelFactory).get(WorkoutExercisesViewModel::class.java)
        binding.workoutExercisesViewModel = workoutExercisesViewModel


        val adapter = ExerciseAdapter(ExerciseListener {
            exerciseID -> Toast.makeText(context, "${exerciseID}", Toast.LENGTH_SHORT).show()
        })
        binding.exercisesRecycleView.adapter = adapter

        workoutExercisesViewModel.allExercises.observe(viewLifecycleOwner, Observer {
            //changes this to implement listadapter
            adapter.submitList(it)

        })


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