package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Exercise
import com.anthonyra95.android.myworkouttracker.database.Workout
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao
import kotlinx.coroutines.*
import kotlin.math.E

class WorkoutExercisesViewModel(private val workingSetKey: Long = 0L, val database: WorkoutDatabaseDao): ViewModel(){
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val _navigateToWorkoutTracker = MutableLiveData<Boolean?>()

    //get all exercises
     val allExercises = database.getAllExercises()
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    init {
        initializeExerciseList()

    }

    private fun initializeExerciseList() {
        //check if the table has beeen populated
        uiScope.launch {
            checkIfExercisesTableIsPopulated()
        }
    }

    private suspend fun checkIfExercisesTableIsPopulated() {
        withContext(Dispatchers.IO) {
            if(allExercises.value?.isEmpty() == true){
                val myExercise = Exercise()
                for (i in 0..10){
                    myExercise.exerciseId = i
                    when(i) {
                        0 -> myExercise.exerciseName = "BENCH PRESS "
                        1 -> myExercise.exerciseName = "BARBELL SQUAT"
                        2 -> myExercise.exerciseName = "CURLS"
                        3 -> myExercise.exerciseName = "CABLE FLIES"
                        4 -> myExercise.exerciseName = "CHIN UPS"
                        5 -> myExercise.exerciseName = "LATERAL RAISES"
                        6 -> myExercise.exerciseName = "PUSH UPS"
                        7 -> myExercise.exerciseName = "ROW"
                        8 -> myExercise.exerciseName = "SHOULDER PRESS"
                        9 -> myExercise.exerciseName = "SQUAT"
                        10 -> myExercise.exerciseName = "DIPS"
                        else -> break
                    }
                    insert(myExercise)
                }
            }
        }
    }

    private suspend fun insert(exercise : Exercise){
        withContext(Dispatchers.IO){
            database.insertExercise(exercise)
        }
    }

    val navigateToWorkoutTracker : LiveData<Boolean?>
        get() = _navigateToWorkoutTracker


    fun doneNavigating(){
        _navigateToWorkoutTracker.value = null
    }

    fun onSetExerciseid(exerciseId: Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val currentSet = database.getWorkingSet(workingSetKey)
                currentSet.exerciseId = exerciseId
                database.updateEntry(currentSet)
            }
            _navigateToWorkoutTracker.value = true
        }
    }

}
