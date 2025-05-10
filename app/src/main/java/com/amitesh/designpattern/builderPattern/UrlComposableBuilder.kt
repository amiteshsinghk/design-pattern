package com.amitesh.designpattern.builderPattern

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//In this pattern we are only concerned about what we need from the class and not everything that a class has.


//You have a base url, create a builder pattern which take url and return composable function.
//val imageComposable = UrlComposableBuilder.Builder()
//                    .setUrl("https://google.com")
//                    .build()
class UrlComposableBuilder {

    class Builder(){
        private var urls: String = ""

        fun setUrl(url: String): Builder {
            this.urls = url
            return this
        }


        fun build(): @Composable () -> Unit {
            return { displayImage(urls) }
        }
    }
}

@Composable
fun displayImage(url: String){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(text = url)
    }
}
