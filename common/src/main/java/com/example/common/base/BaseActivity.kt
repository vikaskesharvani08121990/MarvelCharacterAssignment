package com.example.common.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String) = showToastMessage(message)

}