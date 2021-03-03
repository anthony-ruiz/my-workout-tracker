package com.anthonyra95.android.myworkouttracker.workoutexercises

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anthonyra95.android.myworkouttracker.R
import com.anthonyra95.android.myworkouttracker.database.Exercise
import com.anthonyra95.android.myworkouttracker.database.TextItemViewHolder


class ExerciseAdapter  : RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<Exercise>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.exerciseId.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.basic_text_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
    
}