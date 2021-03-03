package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Exercise
import com.anthonyra95.android.myworkouttracker.database.converExerciseIdToString


class ExerciseAdapter  : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    var data = listOf<Exercise>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    class ViewHolder private constructor (itemView: View) : RecyclerView.ViewHolder(itemView){
        val exercise: TextView= itemView.findViewById(R.id.exercise_name)

        fun bind(item: Exercise) {
            var res = itemView.context.resources
            exercise.text = converExerciseIdToString(item.exerciseId)
            //todo set the image
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_items_exercises, parent, false)

                return ViewHolder(view)
            }
        }
    }


}