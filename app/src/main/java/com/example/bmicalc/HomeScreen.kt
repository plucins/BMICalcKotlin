package com.example.bmicalc

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeScreen : AppCompatActivity() {
    private var height: EditText? = null
    private var weight: EditText? = null
    private var age: EditText? = null
    private var result: TextView? = null
    private var radioSexGroup: RadioGroup? = null
    private var radioSexButton: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
        age = findViewById(R.id.age)
        result = findViewById(R.id.result)
    }

    fun calculateBMI(v: View?) {
        val heightStr = height!!.text.toString()
        val weightStr = weight!!.text.toString()
        val ageStr = age!!.text.toString()
        if (heightStr != null && "" != heightStr
                && weightStr != null && "" != weightStr) {
            val heightValue = heightStr.toFloat() / 100
            val weightValue = weightStr.toFloat()
            val bmi = weightValue / (heightValue * heightValue)
            val sex = addListenerOnButton()
            val ppm = calculatePPM(heightStr, weightStr, ageStr, sex)
            displayBMI(bmi, ppm)
        }
    }

    private fun calculatePPM(heightStr: String, weightStr: String, ageStr: String, sex: String): String {
        val height = Integer.getInteger(heightStr)!!
        val weight = Integer.getInteger(weightStr)!!
        val age = Integer.getInteger(ageStr)!!
        var result = 0.0
        if (sex == "Kobieta") {
            result = 655.1 + 9.563 * weight + 1.85 * height - 4.676 * age
        } else if (sex == "Mężczyzna") {
            result = 66.5 + 13.75 * weight + 5.003 * height - 6.775 * age
        }
        return result.toString()
    }

    private fun displayBMI(bmi: Float, ppm: String) {
        var bmiLabel = ""
        bmiLabel = if (java.lang.Float.compare(bmi, 15f) <= 0) {
            getString(R.string.wyglodzenie)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 16f) <= 0) {
            getString(R.string.wychudzenie)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 18.5f) <= 0) {
            getString(R.string.niedowaga)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 25f) <= 0) {
            getString(R.string.wartosc_prawidlowa)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 30f) <= 0) {
            getString(R.string.nadwaga)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 35f) <= 0) {
            getString(R.string.I_stopien_otylosci)
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi, 40f) <= 0) {
            getString(R.string.II_stopien_otylosci)
        } else {
            getString(R.string.otyłosc_skrajna)
        }
        bmiLabel = "$bmi\n\n$bmiLabel\n\nPPM\n\n$ppm"
        result!!.text = bmiLabel
    }

    fun addListenerOnButton(): String {
        radioSexGroup = findViewById<View>(R.id.radioSex) as RadioGroup


        // get selected radio button from radioGroup
        val selectedId = radioSexGroup!!.checkedRadioButtonId

        // find the radiobutton by returned id
        radioSexButton = findViewById<View>(selectedId) as RadioButton
        return radioSexButton!!.text.toString()
    }
}