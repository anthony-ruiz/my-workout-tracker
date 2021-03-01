package com.anthonyra95.android.myworkouttracker.workoutracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anthonyra95.android.myworkouttracker.database.WorkoutDatabaseDao

class WorkoutTrackerViewModelFactory (
    private val dataSource: WorkoutDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutTrackerViewModel::class.java)) {
            return WorkoutTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
