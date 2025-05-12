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