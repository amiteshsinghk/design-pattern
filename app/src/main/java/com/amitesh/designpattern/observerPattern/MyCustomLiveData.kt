package com.amitesh.designpattern.observerPattern

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Suppress("UNCHECKED_CAST")
class MyCustomLiveData<T> {

    private var dataHolder: T? = null
    private val observers = mutableListOf<(T) -> Unit>()

    fun getValue() = dataHolder

    fun setValue(value: T) {
        dataHolder = value
        observers.forEach {
            it.invoke(dataHolder as T)
        }
    }

    fun addObserver(observer: (T) -> Unit) {
        observers.add(observer)
    }
}

@Suppress("UNCHECKED_CAST")
class MyCustomLiveDataLifecycle<T>{
    private var dataHolder: T? = null
    private var observers = HashMap<(T) -> Unit, LifecycleOwnerWrapper>()
    private var mainScope = MainScope()
    private var mutex = Mutex()

    fun getValue() = dataHolder

    fun setValue(value: T){
        mainScope.launch(Dispatchers.Main.immediate){
            mutex.withLock {
                dataHolder = value
                observers.forEach { observer, owner ->
                    if (owner.lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                        observer.invoke(dataHolder as T)
                    }
                }
            }
        }
    }

    fun addObserver(lifecycleOwner: LifecycleOwner, observer:(T) -> Unit){
        mainScope.launch{
            mutex.withLock {
                LifecycleOwnerWrapper(lifecycleOwner, observer).apply {
                    this.lifecycleOwner.lifecycle.addObserver(this)
                    observers[observer] = this
                }
            }
        }
    }

    fun notifyObserver(observer: (T) -> Unit){
        mainScope.launch{
            mutex.withLock{
                observer.invoke(dataHolder as T)
            }
        }

    }

    fun removeObserver(observer: (T) -> Unit){
        mainScope.launch{
            mutex.withLock {
                observers.remove(observer)
            }
        }.apply {
            if (isCompleted) mainScope.cancel()
        }
    }

    inner class LifecycleOwnerWrapper(val lifecycleOwner: LifecycleOwner, val observer: (T) -> Unit): DefaultLifecycleObserver{
        override fun onStart(owner: LifecycleOwner) {
            notifyObserver(observer)
        }

        override fun onResume(owner: LifecycleOwner) {
            notifyObserver(observer)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            removeObserver(observer)
        }

    }
}

/*
class MyCustomLiveDataLifecycle<T> { // created a generic class to hold and distribute the data of type T
    private var dataHolder: T? = null // Holds the current value of the data
    private val observers =
        HashMap<(T) -> Unit, LifecycleOwnerWrapper>() // A HashMap that maps each observer function to a corresponding LifecycleOwnerWrapper (which tracks its lifecycle and manages callbacks).

    fun getValue() = dataHolder // Returns the current value held in dataHolder.
    fun setValue(value: T?) {
        dataHolder = value // Sets the new value of the live data.

//  Iterates through each observer.
//  If the associated LifecycleOwner is in STARTED or higher state, it calls the observer's function with the current value.
//  dataHolder as T uses a safe unchecked cast, assuming it's not null when notifying.
        observers.forEach { observer, owner ->
            if (owner.lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                observer.invoke(dataHolder as T)
            }
        }
    }

//  Adds a new observer along with the lifecycle it should be bound to.
//  Creates a LifecycleOwnerWrapper (which implements lifecycle event methods).
//  Adds it as a lifecycle observer to receive events like ON_START, ON_RESUME, and ON_DESTROY.
//  Stores this wrapper in the map with the observer function as key.
    fun addObserver(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        LifecycleOwnerWrapper(lifecycleOwner, observer).apply {
            this.lifecycleOwner.lifecycle.addObserver(this)
            observers[observer] = this
        }
    }

    //  Calls the observer’s function directly with the current value (if not null).
    fun notifyObserver(observer: (T) -> Unit) {
        observer.invoke(dataHolder as T)
    }

    //  Removes the observer from the map — also implies that it won’t be notified anymore.
    fun removeObserver(observer: (T) -> Unit) {
        observers.remove(observer)
    }

    private inner class LifecycleOwnerWrapper(
        val lifecycleOwner: LifecycleOwner,
        val observer: (T) -> Unit
    ) : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {
            notifyObserver(observer)
        }

        override fun onResume(owner: LifecycleOwner) {
            notifyObserver(observer)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            removeObserver(observer)
        }
    }
}
*/






































