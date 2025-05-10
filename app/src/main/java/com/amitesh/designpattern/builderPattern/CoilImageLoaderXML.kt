package com.amitesh.designpattern.builderPattern

import android.content.Context
import android.provider.MediaStore
import android.widget.ImageView
import coil3.load

//In this pattern we are only concerned about what we need from the class and not everything that a class has.

//XmlImageLoader.with(context)
//.load("https://picsum.photos/300")
//.placeholder(R.drawable.placeholder)
//.into(findViewById(R.id.imageView))
//.build()

class CoilImageLoaderXML {

    class Builder(context: Context){
        private var url : String =""
        private var imageview : ImageView? = null

        fun load(url: String): Builder{
            this.url = url
            return this
        }
        fun into(imageView: ImageView): Builder{
            this.imageview = imageView
            return this
        }

        fun build(){
            imageview?.load(url)
        }
    }

    companion object{
        fun with(context: Context) = Builder(context)
    }

}