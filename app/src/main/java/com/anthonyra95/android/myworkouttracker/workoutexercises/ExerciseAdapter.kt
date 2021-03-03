package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.database.Exercise
import com.anthonyra95.android.myworkouttracker.databinding.ListItemsExercisesBinding


class ExerciseAdapter(val clicListener:ExerciseListener): ListAdapter<Exercise, ExerciseAdapter.ViewHolder>(ExercisesDifCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clicListener)
    }


    class ViewHolder private constructor (val binding: ListItemsExercisesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Exercise, clicListener: ExerciseListener) {
            binding.exercise = item
            binding.clickListener = clicListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemsExercisesBinding.inflate(layoutInflater,parent,false)

                return ViewHolder(binding)
            }
        }
    }
}
class ExercisesDifCallBack : DiffUtil.ItemCallback<Exercise>(){
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.exerciseId == newItem.exerciseId
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return  oldItem== newItem
    }
}

class ExerciseListener(val clickListener: (exerciseID: Int) ->Unit){
    fun onClick(exercise: Exercise) = clickListener(exercise.exerciseId)
}