package com.amitesh.designpattern

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.amitesh.designpattern.builderPattern.CoilImageLoaderCompose
import com.amitesh.designpattern.builderPattern.CoilImageLoaderXML
import com.amitesh.designpattern.builderPattern.UrlComposableBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        composeView()
        loadImage()
        viewModel.myCustomLiveData.addObserver {
            setTextData(it.toString())
        }
        viewModel.myCustomLiveDataLifecycle.addObserver(this) {
            setTextDataLifecycle(it.toString())
        }
        viewModel.incrementCounter()
    }

    fun loadImage(){
        val imageView = findViewById<ImageView>(R.id.image_view)
        CoilImageLoaderXML
            .with(this)
            .load("https://images.tv9marathi.com/wp-content/uploads/2025/05/India-vs-Pakistan-war.jpg")
            .into(imageView)
            .build()
    }

    fun composeView(){
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MaterialTheme {


                Box(modifier = Modifier
                    .size(200.dp)

                ){
                    Column (
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        SetTextUrl()
                        SetImage()
                    }

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

    @Composable
    fun  SetTextUrl(){
         UrlComposableBuilder.Builder()
        .setUrl("https://google.com")
        .build()
        .invoke()
    }
    @Composable
    fun SetImage() {
         CoilImageLoaderCompose
            .Builder()
            .load("https://images.tv9marathi.com/wp-content/uploads/2025/05/India-vs-Pakistan-war.jpg")
            .contentDescription("Image")
            .build()
            .invoke()
    }
}