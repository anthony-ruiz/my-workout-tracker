package com.anthonyra95.android.myworkouttracker.workoutracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.TextItemViewHolder
import com.anthonyra95.android.myworkouttracker.database.Workout

class WorkoutAdapter : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    var data = listOf<Workout>()

        //tell recycler view that he data set changed
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    //recylcer view needs to know how many items there are
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.workoutId.text = item.exerciseId.toString()
       // holder.workoutId.text  TODO TRansform the ID into exercise name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_working_sets, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val workoutId : TextView = itemView.findViewById(R.id.exercise_name_text)
        //TODO add all of the other data that is needed for the viewholder
    }

}