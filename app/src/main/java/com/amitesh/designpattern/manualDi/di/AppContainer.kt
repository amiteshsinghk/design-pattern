package com.amitesh.designpattern.manualDi.di

import androidx.lifecycle.ViewModelProvider
import com.amitesh.designpattern.MyViewModel
import com.amitesh.designpattern.manualDi.UserRepository
import com.amitesh.designpattern.manualDi.UserRepositoryImpl

class AppContainer {

//    val userRepository: UserRepository = UserRepositoryImpl()
    val userRepository: UserRepository by lazy{
    UserRepositoryImpl()
}

    fun provideMainViewModelFactory(): ViewModelProvider.Factory{
        return GenericViewModelFactory{
            MyViewModel(userRepository)
        }
    }
}