package com.sportzinteractive.baseprojectsetup.data.model.auth

sealed class SignOutState {
    class Success(val message: String) : SignOutState()
    class Failure(val message: String) : SignOutState()
}