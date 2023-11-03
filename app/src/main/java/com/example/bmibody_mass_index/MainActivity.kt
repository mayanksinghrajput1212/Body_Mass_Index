package com.example.bmibody_mass_index

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bmibody_mass_index.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val weight = binding.weightNumber.text.toString()
            val height = binding.heightNumber.text.toString()
            if (vailid(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result with two decimal places
                val bmi2Digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digit)

                hideKeyboard()

            }
        }

    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus

        if (currentFocusView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }
    }

    private fun vailid(weight: String?, height: String?): Boolean {
    return when {
        weight.isNullOrEmpty() -> {
            Toast.makeText(this, "weight is empty", Toast.LENGTH_SHORT).show()
            return false
        }

        height.isNullOrEmpty() -> {
            Toast.makeText(this, "height is empty", Toast.LENGTH_SHORT).show()
            return false
        }

        else -> {
            return true

        }
    }

}


private fun displayResult(bmi: Float) {
    binding.textResultIndex.text = bmi.toString()
    binding.textResultinfo.text = "normal range is 18.20 - 24.39 "
    var resultText = ""
    var color = 0
    when {
        bmi < 18.50 -> {
            resultText = " Underweight"
            color = R.color.under_weight
        }

        bmi in 18.50..24.99 -> {
            resultText = " Healthy"
            color = R.color.normal
        }

        bmi in 25.00..29.99 -> {
            resultText = " Overweight"
            color = R.color.over_weight
        }

        bmi > 29.99 -> {
            resultText = " Obese"
            color = R.color.obses
        }
    }
    binding.textResultDescri.setTextColor(ContextCompat.getColor(this, color))
    binding.textResultDescri.text = resultText
}

}