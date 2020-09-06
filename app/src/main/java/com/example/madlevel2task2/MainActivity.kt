package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.inputQuestion

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    fun initViews() {
        binding.btnAdd.setOnClickListener {
            val question = binding.inputQuestion.text.toString()
            var isTrue = false
            if (binding.isTrueBtn.isChecked) {
                isTrue = true
            }

            addQuestion(question, isTrue)
        }
        binding.listQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.listQuestions.adapter = questionAdapter
        binding.listQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(listQuestions)
    }

    private fun addQuestion(question: String, isTrue: Boolean) {
        if (question.isNotBlank()) {
            questions.add(Question(question, isTrue))
            questionAdapter.notifyDataSetChanged()
            binding.inputQuestion.text?.clear()
        } else {
            Snackbar.make(inputQuestion, "You must fill in the input field!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    if (!questions[position].isTrue) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        Snackbar.make(
                            inputQuestion,
                            "Wrong, will not be removed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        questionAdapter.notifyDataSetChanged()
                    }
                }
                if(direction == ItemTouchHelper.RIGHT){
                    if (questions[position].isTrue) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        Snackbar.make(
                            inputQuestion,
                            "Wrong, will not be removed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        questionAdapter.notifyDataSetChanged()
                    }
                }

            }
        }
        return ItemTouchHelper(callback)
    }
}