package com.first.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var isFirstInput: Boolean = true  //처음 입력시 true
    private var resultNumber: Int = 0  //결과 초기값
    private var opResult: Char = '+'  //초기 연산자
    private val CLEAR_INPUT_TEXT: String = "0"  //계산기의 clear를 눌렀을때 0이 되는걸 상수로 표현

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultText = findViewById(R.id.result_text)
    }

    fun buttonClick(view: View) {  //버튼 클릭 리스너

        if (view.id == R.id.all_clear_button) { //모두 지우기 버튼을 눌렀을 경우
            isFirstInput = true
            resultNumber = 0
            opResult = '+'
            resultText.setTextColor(Color.parseColor("#666666"))
            resultText.text = resultNumber.toString()
        }

        when (view.id) {  //자바에선 switch 이지만 코틀린에서 when이라 쓰이길래 애먹었습니다..
            R.id.all_clear_button -> {
                resultNumber = 0
                opResult = '+'
                setClearText(CLEAR_INPUT_TEXT)
            }
            R.id.clear_entry_button -> {
                setClearText(CLEAR_INPUT_TEXT)
            }
            R.id.back_sp_button -> {   //back 버튼을 누르면 하나씩 지워지는 부분
                if (resultText.text.toString().length > 1) {
                    var getResultText: String = resultText.text.toString()
                    var subString: String = getResultText.substring(0, getResultText.length - 1)
                    resultText.setText(subString)
                } else {
                    setClearText(CLEAR_INPUT_TEXT)
                }
            }
            R.id.decimal_button -> {
                Log.e("buttonClick",  "decimal_button 버튼이 클릭되었습니다")
            }


        }

    }
    //입력된 숫자를 클리어
    fun setClearText(clearText: String) {
        resultText.setTextColor(Color.parseColor("#666666"))
        isFirstInput = true
        resultText.setText(clearText)
    }
    //0~9 버튼 클릭 메소드
    fun numButtonClick(view: View) {
        val getbutton = findViewById<Button>(view.id)
        if (isFirstInput) {
            resultText.setTextColor(-16777216)
            resultText.setText(getbutton.text.toString())
            isFirstInput = false
        } else {
            if(resultText.text.toString().equals("0")){
                Toast.makeText(applicationContext,"0으로 시작하는 숫자는 없음",Toast.LENGTH_LONG).show()
                setClearText("0")
                return
            }
            resultText.append(getbutton.text.toString())
            // Log.e("buttonClick", "default $getbutton 버튼이 클릭 되었습니다")
        }
    }
    //연산자가 클리 됐을때 실행
    fun operatorClick(view: View) {
        val getbutton = findViewById<Button>(view.id)

        if (view.id == R.id.result_text) {
            if(isFirstInput){
                resultNumber = 0
                opResult = '+'
                setClearText(CLEAR_INPUT_TEXT)
                // TODO: 2023-07-18 다음 실수형 계산기 만들때 윈도우 계산기 처럼 =을 두번 이상 누를때 실행 방법과 같이 구현!
            }else{
                resultNumber =
                    intCal(resultNumber, Integer.parseInt(resultText.text.toString()), opResult)
                resultText.setText(resultNumber.toString())
                isFirstInput = true
            }

        } else {
            if(isFirstInput){
                opResult = getbutton.text.toString()[0]
            }else{
                var lastNum = Integer.parseInt(resultText.text.toString())
                resultNumber = intCal(resultNumber, lastNum, opResult)
                opResult = getbutton.text.toString()[0]
                resultText.setText(resultNumber.toString())
                isFirstInput = true
            }


        }


    }

    // 사칙연산 메소드
    fun intCal(result: Int, lastNum: Int, operator: Char): Int {
        var calculatedResult = result

        if (operator == '+') {
            calculatedResult += lastNum
        } else if (operator == '-') {
            calculatedResult -= lastNum
        } else if (operator == '/') {
            calculatedResult /= lastNum
        } else if (operator == '*') {
            calculatedResult *= lastNum
        }

        return calculatedResult
    }


}
