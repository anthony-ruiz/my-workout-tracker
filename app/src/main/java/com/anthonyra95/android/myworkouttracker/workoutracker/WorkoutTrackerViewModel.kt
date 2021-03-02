package com.anthonyra95.android.myworkouttracker.workoutracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.anthonyra95.android.myworkouttracker.database.Workout
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao
import com.anthonyra95.android.myworkouttracker.database.formatWorkouts
import kotlinx.coroutines.*


//we are going to use corrutines
class WorkoutTrackerViewModel(
    val database: WorkoutDatabaseDao, application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()    //manages our coroutines

    override fun onCleared() {          //its called when the viewmodel is destoyed
        super.onCleared()
        viewModelJob.cancel()
    }

    //Scope our thread is going to run in
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var workoutStartingTime : Long = 0L

    //current workout
    private lateinit var todaysWorkout : LiveData<List<Workout>>

    //all of the working sets on our database
    private val allworkouts = database.getAllEnries()

    val workoutString = Transformations.map(allworkouts){workouts->
        formatWorkouts(workouts,application.resources)
    }


    init {
        initializeTodaysWorkout()
    }

    //uses a corutine to get our workout from database so that it doesnt block ui
    private fun initializeTodaysWorkout() {
        uiScope.launch {

            todaysWorkout = getTodaysWorkoutFromDatabase()
        }
    }

    //suspend is bc it is called from the corutine
    private suspend fun getTodaysWorkoutFromDatabase(): LiveData<List<Workout>> {
        return withContext(Dispatchers.IO){
            var currentWorkout = database.getCurrentWorkout()
//            if(database.getLatestEntry().endTimeMilli != database.getLatestEntry().startTimeMilli){
//                currentWorkout = null
//            }
            currentWorkout
        }
    }

    fun onStartWorkout(){
        uiScope.launch {
            Log.i("thisisit", "clicked registered")
            var newWorkout = Workout()
            if(workoutStartingTime != 0L){
                newWorkout.startTimeMilli = workoutStartingTime
                newWorkout.endTimeMilli = workoutStartingTime

            }
            insert(newWorkout)

            todaysWorkout = getTodaysWorkoutFromDatabase()
        }
    }
    private suspend fun insert(workout: Workout){
        withContext(Dispatchers.IO){
            database.insertWorkout(workout)
            Log.i("thiisit", "you saved item with endtime ${workout.endTimeMilli} and start time: ${workout.startTimeMilli}")
        }
    }

    fun onStopWorkout(){
        uiScope.launch {
            var myList : List<Workout>? = null
               Transformations.map(todaysWorkout){
                   if(it.isEmpty()) return@map
                   myList = it
                   it.forEach {
                       it.endTimeMilli = System.currentTimeMillis()
                   }

               }
            update(myList)
        }
    }

    private suspend fun update(workout: List<Workout>?){
        val currentWorkout = workout ?: return
        currentWorkout.forEach {
            database.updateEntry(it)
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            todaysWorkout = getTodaysWorkoutFromDatabase()
        }
    }

    suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }


}
