package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView





class BoardActivity : AppCompatActivity(), View.OnClickListener {

    private var n:Int = 3
    private var count:Int = 0
    private val player1:String = "x"
    private val player2:String = "o"
    private var current:String = "x"
    private var isEnd:Boolean = false
    private var buttons: Array<Array<Button>>? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        n = intent.getStringExtra("size").toInt()
        buttons = Array(n) { Array<Button>(n) {Button(this)} }
        textView = TextView(this)
        createBoard(n)
    }

    fun createBoard(size:Int){

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        var resId = 0
        for(i in 0 until size){
            val layoutRow = LinearLayout(this)
            layoutRow.orientation = LinearLayout.HORIZONTAL
            for(j in 0 until size){
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                buttons!![i][j].id = ++resId

                params.setMargins(3, 3, 3, 3)
                buttons!![i][j].layoutParams = params
                buttons!![i][j].setOnClickListener(this)
                layoutRow.addView(buttons!![i][j])
            }
            layout.addView(layoutRow)
        }
        textView!!.gravity = Gravity.CENTER
        textView!!.textSize = 30.0F
        layout.addView(textView)
        val layoutParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        this.addContentView(layout, layoutParam)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if(isEnd) return
            var resId = 0
            for(i in 0 until n)
                for(j in 0 until n) {
                    if(++resId == v.id){
                        if(buttons!![i][j].text == "") {
                            buttons!![i][j].text = current
                            isEnd = isWinning(i, j, current)
                            if (isEnd && !isTurn()) {
                                textView!!.text = "$current win"
                            } else if (isTurn()) {
                                textView!!.text = "Draw"
                                isEnd = true
                            }
                            current = isTurnOf()
                        }
                    }
                }
        }

    }

    fun isTurnOf(): String{
        if(player1 == current) return player2
        else return player1
    }

    fun isTurn(): Boolean{
        return count == n*n
    }

    fun isWinning(x:Int,y:Int,player:String): Boolean {
        for(i in 0 until n){
            if(buttons!![x][i].text != player) break
            if(i == n - 1) return true
        }

        for(i in 0 until n){
            if(buttons!![i][y].text != player) break
            if(i == n - 1) return true
        }

        if(x==y){
            for(i in 0 until n){
                if(buttons!![i][i].text != player) break
                if(i == n - 1) return true
            }
        }

        if(x+y == n - 1){
            for(i in 0 until n){
                if(buttons!![i][(n - 1) - i].text != player) break
                if(i == n - 1) return true
            }
        }

        return false
    }
}
