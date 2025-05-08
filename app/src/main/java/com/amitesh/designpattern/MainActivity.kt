package com.amitesh.designpattern

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.amitesh.designpattern.builderPattern.UrlComposableBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        composeView()
        viewModel.myCustomLiveData.addObserver {
            setTextData(it.toString())
        }
        viewModel.myCustomLiveDataLifecycle.addObserver(this) {
            setTextDataLifecycle(it.toString())
        }
        viewModel.incrementCounter()
    }

    fun composeView(){
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MaterialTheme {
                val imageComposable = UrlComposableBuilder.Builder()
                    .setUrl("https://google.com")
                    .build()

                Box(modifier = Modifier
                    .fillMaxWidth()){
                    imageComposable()
                }
            }
        }
    }

    fun setTextData(value: String) {
        var textField = findViewById<TextView>(R.id.custom_text)
        textField.text = value
    }

    fun setTextDataLifecycle(value: String) {
        var textField = findViewById<TextView>(R.id.custom_text_lifecycle)
        textField.text = value
    }
}