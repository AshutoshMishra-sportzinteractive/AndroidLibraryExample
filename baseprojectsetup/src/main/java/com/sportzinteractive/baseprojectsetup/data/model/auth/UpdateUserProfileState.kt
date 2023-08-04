package com.sportzinteractive.baseprojectsetup.data.model.auth

sealed class UpdateUserProfileState {
    class Success(val user:User) : UpdateUserProfileState()
    class Failure(val code:Int?,val message: String) : UpdateUserProfileState()
}