package com.anthonyra95.android.myworkouttracker.workoutexercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao
import java.lang.IllegalArgumentException


class WorkoutExercisesVeiwModelFactory(
    private val workingSetKey: Long,
    private val dataSource: WorkoutDatabaseDao) : ViewModelProvider.Factory{

    @Suppress("uncheked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WorkoutExercisesViewModel::class.java)){
            return WorkoutExercisesViewModel(workingSetKey,dataSource) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }
}