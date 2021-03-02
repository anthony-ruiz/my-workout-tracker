package com.anthonyra95.android.myworkouttracker.workoutracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Workout

class WorkoutAdapter : ListAdapter<Workout, WorkoutAdapter.ViewHolder>(WorkoutDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val workoutId : TextView = itemView.findViewById(R.id.exercise_name_text)
        //TODO add all of the other data that is needed for the viewholder

        fun bind(item: Workout){
            val res = itemView.context.resources
            workoutId.text = item.exerciseId.toString()
            // holder.workoutId.text  TODO TRansform the ID into exercise name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_working_sets, parent, false)
                return ViewHolder(view)
            }
        }
    }

    class WorkoutDiffCallback : DiffUtil.ItemCallback<Workout>(){
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.entryId == newItem.entryId
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }

    }

}