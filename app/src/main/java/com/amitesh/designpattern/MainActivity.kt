package com.amitesh.designpattern

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.myCustomLiveData.addObserver {
            setTextData(it.toString())
        }
        viewModel.incrementCounter()
    }

    fun setTextData(value: String) {
        var textField = findViewById<TextView>(R.id.custom_text)
        textField.text = value
    }
}