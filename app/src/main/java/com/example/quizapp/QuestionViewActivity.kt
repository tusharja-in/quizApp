package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionViewActivity : AppCompatActivity(),View.OnClickListener {
    private var mCurrentPosition=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOptionPosition=0
    private var isOptionSelected:Boolean=false
    private var isSubmitted=false
    private var score:Int=0
    var mUserName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        mUserName=intent.getStringExtra(Constants.USER_NAME)
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
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

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
        val btn_submit=findViewById<Button>(R.id.btn_submit)
        when(p0?.id){

            R.id.tv_option_one ->{
                if(!isSubmitted) {
                    selectedOptionView(tv_option_one, 1)
                    isOptionSelected = true
                }
            }
            R.id.tv_option_two ->{
                if(!isSubmitted) {
                    selectedOptionView(tv_option_two, 2)
                    isOptionSelected = true
                }
            }
            R.id.tv_option_three ->{
                if(!isSubmitted) {
                    selectedOptionView(tv_option_three, 3)
                    isOptionSelected = true
                }
            }
            R.id.tv_option_four ->{
                if(!isSubmitted) {
                    selectedOptionView(tv_option_four, 4)
                    isOptionSelected = true
                }
            }
            R.id.btn_submit -> {
                if (isOptionSelected) {
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++
                        if (mCurrentPosition <= mQuestionList!!.size) {
                            setQuestion()
                            Log.i("Test","$mUserName")
                        } else {
                            val intent =Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.CORRECT_ANSWERS,score.toString())
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size.toString())
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                            isSubmitted = true
                            val question: Question = mQuestionList!![mCurrentPosition - 1]
                            val correctAns = question.correctAnswer
                            if (mSelectedOptionPosition != correctAns) {
                                answerView(mSelectedOptionPosition, R.drawable.wrong_options_border)
                            }
                            else{
                            score++
                            }
                            answerView(correctAns, R.drawable.correct_options_border)
                            if (mCurrentPosition == mQuestionList!!.size) {
                                btn_submit.text = "FINISH"
                            } else {
                                btn_submit.text = "GO TO NEXT QUESTION"
                            }
                            mSelectedOptionPosition = 0
                    }
                }
                else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        isSubmitted=false
        isOptionSelected=false

        btn_submit.text="SUBMIT"
        tv_question.text=question.ques
        iv_flag.setImageResource(question.image)
        progressBar.progress=mCurrentPosition
        tv_progress.text= mCurrentPosition.toString() + "/" + progressBar.max
        tv_option_one.text=question.optionOne
        tv_option_two.text=question.optionTwo
        tv_option_three.text=question.optionThree
        tv_option_four.text=question.optionFour

    }

    private fun selectedOptionView(tv:TextView,selectedPos:Int){
        defaultOptionsView()
        mSelectedOptionPosition=selectedPos
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.select_options_border)
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


    private fun answerView(answer:Int,drawableView:Int){
        val tv_option_one=findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two=findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three=findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four=findViewById<TextView>(R.id.tv_option_four)
        when(answer){
            1->{
                tv_option_one.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                tv_option_two.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                tv_option_three.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                tv_option_four.background=ContextCompat.getDrawable(this,drawableView)
            }
        }
    }


}
