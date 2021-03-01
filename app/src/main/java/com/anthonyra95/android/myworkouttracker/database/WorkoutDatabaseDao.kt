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
    fun update(workout: Workout)

    @Query("SELECT * FROM workout_table WHERE workout_id = :key")
    fun getThisWorkout(key: Long) : LiveData<List<Workout>>

    @Query("SELECT * from workout_table WHERE entryId = :key")
    fun getSingleExerciseonDay(key: Long): Workout?

    @Query("DELETE FROM workout_table")
    fun clear()

    @Query("SELECT * FROM workout_table ORDER BY entryId DESC")
    fun getAllEnries(): LiveData<List<Workout>>


}