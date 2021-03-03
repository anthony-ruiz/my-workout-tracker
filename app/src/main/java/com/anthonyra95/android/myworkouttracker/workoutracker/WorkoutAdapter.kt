package com.anthonyra95.android.myworkouttracker.workoutracker

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.database.Workout
import com.anthonyra95.android.myworkouttracker.databinding.ListItemWorkingSetsBinding

class WorkoutAdapter : ListAdapter<Workout, WorkoutAdapter.ViewHolder>(WorkoutDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder private constructor(val binding: ListItemWorkingSetsBinding): RecyclerView.ViewHolder(binding.root){
        //TODO add all of the other data that is needed for the viewholder

        fun bind(item: Workout){
           binding.workingset = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWorkingSetsBinding.inflate(layoutInflater,parent,false)

                return ViewHolder(binding)
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