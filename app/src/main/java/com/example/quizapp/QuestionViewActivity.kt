package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuestionViewActivity : AppCompatActivity(),View.OnClickListener {
    private var mCurrentPosition=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOptionPosition=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_view)
        mQuestionList = Constants.getQuestions()
        setQuestion()
        val tv_question=findViewById<TextView>(R.id.tv_question)
        val iv_flag=findViewById<ImageView>(R.id.iv_flag)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        val tv_progress=findViewById<TextView>(R.id.tv_progress)
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question:Question=mQuestionList!![mCurrentPosition-1]
        defaultOptionsView()
        val tv_question=findViewById<TextView>(R.id.tv_question)
        val iv_flag=findViewById<ImageView>(R.id.iv_flag)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        val tv_progress=findViewById<TextView>(R.id.tv_progress)
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)

        tv_question.text=question.ques
        iv_flag.setImageResource(question.image)
        progressBar.progress=mCurrentPosition
        tv_progress.text= mCurrentPosition.toString() + "/" + progressBar.max
        tv_option_one.text=question.optionOne
        tv_option_two.text=question.optionTwo
        tv_option_three.text=question.optionThree
        tv_option_four.text=question.optionFour


    }

    private fun defaultOptionsView(){
        val tv_question=findViewById<TextView>(R.id.tv_question)
        val iv_flag=findViewById<ImageView>(R.id.iv_flag)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        val tv_progress=findViewById<TextView>(R.id.tv_progress)
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)
        val options=ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)
        for(op in options){
            op.typeface= Typeface.DEFAULT
            op.background=ContextCompat.getDrawable(this,R.drawable.default_options_border)
        }

    }

    override fun onClick(p0: View?) {
        val tv_question=findViewById<TextView>(R.id.tv_question)
        val iv_flag=findViewById<ImageView>(R.id.iv_flag)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)
        val tv_progress=findViewById<TextView>(R.id.tv_progress)
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)
        when(p0?.id){

            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
        }
    }

    private fun selectedOptionView(tv:TextView,selectedPos:Int){
        defaultOptionsView()
        mSelectedOptionPosition=selectedPos
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.select_options_border)
    }
}
