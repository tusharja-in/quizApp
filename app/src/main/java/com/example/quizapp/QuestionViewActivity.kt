package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuestionViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_view)
        val questionList = Constants.getQuestions()
        Log.i("ques size","${questionList.size}")
        val currentPosition=1
        val question:Question?=questionList[currentPosition-1]

        val tv_question=findViewById<TextView>(R.id.tv_question)
        val iv_flag=findViewById<ImageView>(R.id.iv_flag)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        val tv_progress=findViewById<TextView>(R.id.tv_progress)
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)

        tv_question.text=question!!.ques
        iv_flag.setImageResource(question.image)
        progressBar.progress=currentPosition
        tv_progress.text= currentPosition.toString() + "/" + progressBar.max
        tv_option_one.text=question.optionOne
        tv_option_two.text=question.optionTwo
        tv_option_three.text=question.optionThree
        tv_option_four.text=question.optionFour

    }
}
