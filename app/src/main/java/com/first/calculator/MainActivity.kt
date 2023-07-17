package com.first.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var isFirstInput: Boolean = true
    private var resultNumber: Int = 0
    private var opResult: Char = '+'
    val CLEAR_INPUT_TEXT: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultText = findViewById(R.id.result_text)
    }

    fun buttonClick(view: View) {
        val getbutton: Button = findViewById(view.id)
        Log.e("buttonClick", "buttonClick 시 " + getbutton.text.toString())
        Log.d("buttonClick", "resultNumber = $resultNumber")

        when (view.id) {
            R.id.all_clear_button -> {
                isFirstInput = true
                resultNumber = 0
                opResult = '+'
                resultText.setTextColor(0xFF666666.toInt())
                resultText.setText(CLEAR_INPUT_TEXT)
            }
            R.id.clear_entry_button -> {
                isFirstInput = true
                resultText.setText(CLEAR_INPUT_TEXT)
            }
            R.id.back_sp_button -> {
                if (resultText.text.toString().length > 1) {
                    val getResultText = resultText.text.toString()
                    val subString = getResultText.substring(0, getResultText.length - 1)
                    resultText.text = subString
                } else {
                    resultText.setTextColor(0xFF666666.toInt())
                    resultText.setText(CLEAR_INPUT_TEXT)
                    isFirstInput = true
                }
            }
            R.id.decimal_button -> {
                Log.e("buttonClick", getbutton.text.toString())
            }

            R.id.Addition_button, R.id.subtraction_button, R.id.deivision_button, R.id.multiply_button -> {
                val lastNum = resultText.text.toString().toInt()
                if (opResult == '+') {
                    resultNumber + lastNum
                } else if (opResult == '-') {
                    resultNumber - lastNum
                } else if (opResult == '/') {
                    resultNumber / lastNum
                } else if (opResult == '*') {
                    resultNumber * lastNum
                }
                opResult = getbutton.text.toString()[0]
                resultText.text = resultNumber.toString()
                isFirstInput = true
                Log.d("buttonClick", "addresultNumber = $resultNumber")
            }
            R.id.redsult_button -> {
                if (opResult == '+') {
                    resultNumber += resultText.text.toString().toInt()
                } else if (opResult == '-') {
                    resultNumber -= resultText.text.toString().toInt()
                } else if (opResult == '/') {
                    resultNumber /= resultText.text.toString().toInt()
                } else if (opResult == '*') {
                    resultNumber *= resultText.text.toString().toInt()
                }
                resultText.text = resultNumber.toString()
                isFirstInput = true
                Log.d("buttonClick", "addresultNumber = $resultNumber")
            }

            R.id.num_0_button, R.id.num_1_button, R.id.num_2_button, R.id.num_3_button, R.id.num_4_button,
            R.id.num_5_button, R.id.num_6_button, R.id.num_7_button, R.id.num_8_button, R.id.num_9_button -> {
                if (isFirstInput) {
                    resultText.setTextColor(0xFF000000.toInt())
                    resultText.text = getbutton.text.toString()
                    isFirstInput = false
                    Toast.makeText(this, "Button Clicked!", Toast.LENGTH_LONG).show()
                    Log.e("buttonClick", "default " + getbutton.text.toString() + " 버튼이 클릭 되었습니다")
                } else {
                    resultNumber = (resultNumber.toString() + getbutton.text.toString()).toInt()
                    resultText.text = resultNumber.toString()
                    Log.e("buttonClick", "default " + getbutton.text.toString() + " 버튼이 클릭 되었습니다")
                }
            }
            else -> {
                //Toast.makeText(applicationContext, getbutton.text.toString(), Toast.LENGTH_LONG).show()
                Log.e("buttonClick", "default " + getbutton.text.toString())
            }
        }
    }
    public fun numButtonClick(view: View){

    }
}


