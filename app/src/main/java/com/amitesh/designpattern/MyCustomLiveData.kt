package com.amitesh.designpattern

class MyCustomLiveData<T> {

    private var dataHolder: T? = null
    private val observers = mutableListOf<(T) -> Unit>()

    fun getValue() = dataHolder

    fun setValue(value: T){
        dataHolder = value
        observers.forEach {
            it.invoke(dataHolder as T)
        }
    }

    fun addObserver(observer: (T) -> Unit){
        observers.add(observer)
    }
}
