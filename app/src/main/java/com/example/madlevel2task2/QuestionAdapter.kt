package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.QuestionListBinding

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = QuestionListBinding.bind(itemView)

        fun databind(question: Question) {
            binding.inputQuestion.text = question.questionText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}