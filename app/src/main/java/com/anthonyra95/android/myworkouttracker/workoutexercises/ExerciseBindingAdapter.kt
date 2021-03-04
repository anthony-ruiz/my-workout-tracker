package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Exercise
import com.anthonyra95.android.myworkouttracker.database.Workout


@BindingAdapter("exercise_name")
fun TextView.setExerciseName(item: Exercise?) {
    item?.let {
        text = item.exerciseName
    }
}

@BindingAdapter("exercises_image")
fun ImageView.setExersiceImage(item: Exercise?){
    item?.let {
        setImageResource(when (item.exerciseId) {
            0 -> R.drawable.bench_press
            1 -> R.drawable.berbell_squats
            2 -> R.drawable.bicep_curls
            3 -> R.drawable.cable_flies
            4 -> R.drawable.chin_ups
            5 -> R.drawable.lateral_raises
            6 -> R.drawable.push_ups
            7 -> R.drawable.row
            8 -> R.drawable.sohulder_press
            9 -> R.drawable.squats
            10 -> R.drawable.tricep_dips
            else -> R.drawable.empty
        })
    }
}