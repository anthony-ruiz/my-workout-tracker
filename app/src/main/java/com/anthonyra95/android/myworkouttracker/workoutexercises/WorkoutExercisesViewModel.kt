package com.anthonyra95.android.myworkouttracker.workoutexercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao
import kotlinx.coroutines.*

class WorkoutExercisesViewModel(private val workingSetKey: Long = 0L, val database: WorkoutDatabaseDao): ViewModel(){
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val _navigateToWorkoutTracker = MutableLiveData<Boolean?>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val navigateToWorkoutTracker : LiveData<Boolean?>
        get() = _navigateToWorkoutTracker


    fun doneNavigating(){
        _navigateToWorkoutTracker.value = null
    }

    fun onSetExerciseid(exerciseId: Long){
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
