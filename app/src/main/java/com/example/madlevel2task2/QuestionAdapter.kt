package com.example.madlevel2task2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.QuestionListBinding

class QuestionAdapter(private val questions: List<Question>) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = QuestionListBinding.bind(itemView)

        fun databind(question: Question) {
            binding.inputQuestion.text = question.questionText
        }
    }
}