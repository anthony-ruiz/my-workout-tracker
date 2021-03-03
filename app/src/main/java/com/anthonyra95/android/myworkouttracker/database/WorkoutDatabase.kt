package com.anthonyra95.android.myworkouttracker.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Workout::class, Exercise::class], version = 3, exportSchema = false)
abstract  class WorkoutDatabase : RoomDatabase(){

    abstract  val workoutDatabaseDao : WorkoutDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: WorkoutDatabase? = null
        fun getInstance(context: Context): WorkoutDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "workout_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}