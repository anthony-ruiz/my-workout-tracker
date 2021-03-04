package com.anthonyra95.android.myworkouttracker.workoutracker


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Workout



@BindingAdapter("reps_field")
fun setReps( view : TextView,item: Workout?) {
    item?.let {
        view.text = item.reps.toString()
    }
}

@InverseBindingAdapter(attribute = "reps_field")
fun getReps( view : TextView) : String {
    return view.text.toString()
}

@BindingAdapter(" app:reps_field")
fun setListeners(view: TextView, attrChange: InverseBindingListener) {
    // Set a listener for click, focus, touch, etc.
}


@BindingAdapter("weigth_field")
fun TextView.setWeigth(item: Workout?) {
    item?.let {
        text = item.weigth.toString()
    }
}
//this
@BindingAdapter("exercise_name_text")
fun TextView.setExerciseName(item: Workout?) {
    item?.let {
        when(item.exerciseId){
            0 -> text = context.getString(R.string.bench_press)
            1 -> text = context.getString(R.string.barbell_squat)
            2 -> text = context.getString(R.string.curls)
            3 -> text = context.getString(R.string.cable_flies)
            4 -> text = context.getString(R.string.chin_ups)
            5 -> text = context.getString(R.string.lateral_raises)
            6 -> text = context.getString(R.string.push_ups)
            7 -> text = context.getString(R.string.row)
            8 -> text = context.getString(R.string.sohulder_press)
            9 ->text = context.getString(R.string.squat)
            10 -> text = context.getString(R.string.dips)
            else -> text = context.getString(R.string.no_exercise)
        }

    }
}


@BindingAdapter("exercise_image")
fun ImageView.setExersiceNameString(item: Workout?){
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