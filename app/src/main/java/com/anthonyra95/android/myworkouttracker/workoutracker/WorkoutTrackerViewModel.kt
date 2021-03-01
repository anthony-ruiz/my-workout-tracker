package com.anthonyra95.android.myworkouttracker.workoutracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao

class WorkoutTrackerViewModel(
    val database: WorkoutDatabaseDao,application: Application) : AndroidViewModel(application){

    }
