package com.amitesh.designpattern.manualDi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GenericViewModelFactory<T: ViewModel>(
    private val creator:() -> T
) : ViewModelProvider.Factory {

    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return creator() as V
    }
}

/*
class GenericViewModelFactory<T: ViewModel>(// Ensures that T must be a subclass of ViewModel.
    private val creator:() -> T // A lambda function (no-arg function) that returns an instance of type T.This allows you to pass any ViewModel constructor logic into this factory.
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <V : ViewModel> create(modelClass: Class<V>): V { // override the create() method of `ViewModelProvider.Factory`. `V : ViewModel` → This is the return type expected by Android's ViewModel system.
        return creator() as V // invoke the lambda to get the ViewModel, and cast it to V
    }
}
*/
