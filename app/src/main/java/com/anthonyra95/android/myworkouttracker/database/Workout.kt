package com.anthonyra95.android.myworkouttracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="workout_table")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var entryId: Long = 0L,

    @ColumnInfo(name = "exercise_id") //TODO make it a foreign key
    var exerciseId: Int = -1,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "reps")
    var reps: Int = -1,

    @ColumnInfo(name = "weigth")
    var weigth: Int = -1,

    @ColumnInfo(name = "workout_id")
    var workoutId: Int = -1,
)