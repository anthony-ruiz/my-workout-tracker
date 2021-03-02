package com.anthonyra95.android.myworkouttracker.workoutracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
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


    //current workingSet
    private  var thisWorkingSet = MutableLiveData<Workout?>()

    //all of the working sets on our database
    val allworkouts = database.getAllEnries()

    val workoutString = Transformations.map(allworkouts){workouts->
        formatWorkouts(workouts,application.resources)
    }


    init {
        initializeCurrentWorkingset()
    }

    //uses a corutine to get our workout from database so that it doesnt block ui
    private fun initializeCurrentWorkingset() {
        uiScope.launch {

            //gets the latest working set
            thisWorkingSet.value = getCurrentWorkingSetFromDatabase()
        }
    }

    //checks if our set has been ended or not
    private suspend fun getCurrentWorkingSetFromDatabase(): Workout? {
        return withContext(Dispatchers.IO) {
            var currentSet = database.getLatestEntry()
            if (currentSet?.endTimeMilli != currentSet?.startTimeMilli) {
                currentSet = null
            }
             currentSet
        }
    }

    //use live data trigger navigation
    private val _navigateToExercise = MutableLiveData<Workout>()

    val navigateToExercise: LiveData<Workout>
        get() = _navigateToExercise

    fun doneNavigating(){
        _navigateToExercise.value = null
    }


    fun onStartWorkout(){
        uiScope.launch {
            //create our new workingset
            var newWorkout = Workout()

            //if our previous current set has same start and end time set start end time to same as previous one
            //so that we can distinguish from our workout by starting time
            if(thisWorkingSet.value?.endTimeMilli == thisWorkingSet.value?.startTimeMilli){
                newWorkout.endTimeMilli= thisWorkingSet.value?.endTimeMilli!!
                newWorkout.startTimeMilli= thisWorkingSet.value?.endTimeMilli!!
            }
            // save our new database
            insert(newWorkout)
            //update or latest workingSet
            thisWorkingSet.value = getCurrentWorkingSetFromDatabase()
            //navigate to the exercise selector screen
            _navigateToExercise.value = thisWorkingSet.value
        }
    }
    private suspend fun insert(workout: Workout){
        withContext(Dispatchers.IO){
            database.insertWorkout(workout)
            Log.i("HERE", "you saved item with endtime ${workout.endTimeMilli} and start time: ${workout.startTimeMilli}")
        }
    }

    fun onStopWorkout(){
        uiScope.launch {
            //gets the latest working set
            val lastWorkingSetFromWorkout = thisWorkingSet.value ?: return@launch

//            if(lastWorkingSetFromWorkout.endTimeMilli != lastWorkingSetFromWorkout.startTimeMilli){
                lastWorkingSetFromWorkout.endTimeMilli = System.currentTimeMillis()
//            }
            update(lastWorkingSetFromWorkout)
            thisWorkingSet.value = getCurrentWorkingSetFromDatabase()
            //TODO add functionality so that we go to a wokout summary fragment

        }
    }

    private suspend fun update(workout: Workout){
        database.updateEntry(workout)
    }

    fun onClear(){
        uiScope.launch {
            clear()
            thisWorkingSet.value = getCurrentWorkingSetFromDatabase()
        }
    }

    suspend fun clear(){
        viewModelScope.launch {
            clear()
        }

    }


}
