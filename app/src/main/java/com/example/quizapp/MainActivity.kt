package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val btn_start=findViewById<Button>(R.id.btn_start)
        btn_start.setOnClickListener{
            val nameInput=findViewById<AppCompatEditText>(R.id.nameInput)
            if (nameInput.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent=Intent(this,QuestionViewActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}