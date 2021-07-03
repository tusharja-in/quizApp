package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val username=intent.getStringExtra(Constants.USER_NAME)
        val totalQues=intent.getStringExtra(Constants.TOTAL_QUESTIONS)
        val score=intent.getStringExtra(Constants.CORRECT_ANSWERS)
        val tv_name=findViewById<TextView>(R.id.tv_name)
        val tv_score=findViewById<TextView>(R.id.tv_score)

        tv_name.text=username
        tv_score.text="Your Score is $score out of $totalQues"

        val btn_finish=findViewById<Button>(R.id.btn_finish)

        btn_finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }
}