package com.anthonyra95.android.myworkouttracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkoutDatabaseDao {

    @Insert
    fun insertWorkout(workout: Workout)

    @Update
    fun updateEntry(workout: Workout)

    @Query("SELECT * FROM working_sets_table WHERE entryId = :workingSetId")
    fun getWorkingSet(workingSetId: Long) : Workout

    @Query("DELETE FROM working_sets_table")
    fun clear()

    @Query("SELECT * FROM working_sets_table ORDER BY entryId DESC")
    fun getAllEnries(): LiveData<List<Workout>>

    @Query("SELECT * FROM working_sets_table ORDER BY entryId DESC LIMIT 1")
    fun getLatestEntry(): Workout?

    @Query("SELECT * FROM working_sets_table WHERE  start_time_milli = (SELECT MAX(start_time_milli) FROM working_sets_table)")
    fun getCurrentWorkout() : LiveData<List<Workout>>

//
    @Insert
    fun insertWorkout(exercise: Exercise)

    @Query("SELECT * FROM exercise_table ORDER BY exerciseId ASC")
    fun getAllExercises(): LiveData<List<Exercise>>

}