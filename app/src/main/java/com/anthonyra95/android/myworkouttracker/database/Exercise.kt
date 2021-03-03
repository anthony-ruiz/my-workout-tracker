package com.anthonyra95.android.myworkouttracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="exercise_table")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Int = 0,

    @ColumnInfo(name = "exercise_name")
    var exerciseName: String = "",

    @ColumnInfo(name = "imageId")
    var imageId: Long = 0L
    )