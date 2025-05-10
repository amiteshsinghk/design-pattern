package com.amitesh.designpattern.builderPattern

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest


//CoilUrlImageBuilder
//.Builder()
//.load("https://images.tv9marathi.com/wp-content/uploads/2025/05/India-vs-Pakistan-war.jpg")
//.contentDescription("Image")
//.build()
//.invoke()
class CoilImageLoaderCompose{

    class Builder {
        private  var url: String = ""
        private var drawable: Int? = null
        private var contentDescription: String = ""
        fun load(url: String): Builder{
            this.url = url
            return this
        }

        fun placeHolder(drawable: Int):Builder{
            this.drawable = drawable
            return this
        }

        fun contentDescription(contentDescription: String): Builder{
            this.contentDescription = contentDescription
            return this
        }

        fun build(): @Composable () -> Unit {
            return {
                LoadImage(
                    url = url,
                    contentDescription = contentDescription,
                    placehold = drawable
                )
            }

        }

        @Composable
        private fun LoadImage(
            url: String,
            contentDescription: String,
            placehold: Int?
        ){
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .apply {
                            placehold?.let {
                                placeHolder(it)
                            }
                        }
                        .build()
                ),
                contentDescription= contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}