package com.anthonyra95.android.myworkouttracker.workoutracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.TextItemViewHolder
import com.anthonyra95.android.myworkouttracker.database.Workout

class WorkoutAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Workout>()

        //tell recycler view that he data set changed
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    //recylcer view needs to know how many items there are
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.exerciseId.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.basic_text_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }


}