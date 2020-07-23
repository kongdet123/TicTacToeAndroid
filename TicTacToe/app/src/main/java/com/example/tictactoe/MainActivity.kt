package com.example.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartGame(view: View){
        if(mSize.text.toString().toIntOrNull() != null){
            val size = Integer.parseInt(mSize.text.toString())
            val i = Intent(this@MainActivity, BoardActivity::class.java)
            i.putExtra("size",size.toString())
            startActivity(i)
        }else{
            Toast.makeText(applicationContext,"Please enter size",Toast.LENGTH_LONG).show()
        }

    }
}
