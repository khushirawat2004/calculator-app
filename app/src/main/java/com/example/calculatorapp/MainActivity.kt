package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_main)

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + v.paddingLeft,
                systemBars.top + v.paddingTop,
                systemBars.right + v.paddingRight,
                systemBars.bottom + v.paddingBottom
            )
            insets
        }

        // Find views
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnSubtract = findViewById<Button>(R.id.btn_subtract)
        val btnMultiply = findViewById<Button>(R.id.btn_multiplication)
        val btnDivide = findViewById<Button>(R.id.btn_divide)
        val etA = findViewById<EditText>(R.id.et_A)
        val etB = findViewById<EditText>(R.id.et_B)
        val result = findViewById<TextView>(R.id.result)

        // Set up click listeners
        btnAdd.setOnClickListener {
            performOperation(etA, etB, result, Operation.ADD)
        }
        btnSubtract.setOnClickListener {
            performOperation(etA, etB, result, Operation.SUBTRACT)
        }
        btnMultiply.setOnClickListener {
            performOperation(etA, etB, result, Operation.MULTIPLY)
        }
        btnDivide.setOnClickListener {
            performOperation(etA, etB, result, Operation.DIVIDE)
        }
    }

    private fun performOperation(etA: EditText, etB: EditText, result: TextView, operation: Operation) {
        val a = etA.text.toString().toDoubleOrNull()
        val b = etB.text.toString().toDoubleOrNull()

        if (a == null || b == null) {
            result.text = "Invalid input"
            return
        }

        val r = when (operation) {
            Operation.ADD -> a + b
            Operation.SUBTRACT -> a - b
            Operation.MULTIPLY -> a * b
            Operation.DIVIDE -> {
                if (b == 0.0) {
                    result.text = "Cannot divide by zero"
                    return
                } else {
                    a / b
                }
            }
        }

        result.text = r.toString()
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
