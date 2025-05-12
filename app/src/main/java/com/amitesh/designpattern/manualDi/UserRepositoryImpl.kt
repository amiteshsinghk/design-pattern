package com.amitesh.designpattern.manualDi

class UserRepositoryImpl: UserRepository {
    override fun getUser(): String {
        return  "This is manual Di."
    }
}