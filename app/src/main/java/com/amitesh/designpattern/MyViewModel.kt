package com.amitesh.designpattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitesh.designpattern.observerPattern.MyCustomLiveData
import com.amitesh.designpattern.observerPattern.MyCustomLiveDataLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var counter = 0
    var myCustomLiveData = MyCustomLiveData<Int>()
    var myCustomLiveDataLifecycle = MyCustomLiveDataLifecycle<Int>()

    fun incrementCounter() {
        viewModelScope.launch() {
            while (counter < 5) {
                counter++
                myCustomLiveData.setValue(counter)
                myCustomLiveDataLifecycle.setValue(counter)
                delay(1000)
            }
        }
    }


}