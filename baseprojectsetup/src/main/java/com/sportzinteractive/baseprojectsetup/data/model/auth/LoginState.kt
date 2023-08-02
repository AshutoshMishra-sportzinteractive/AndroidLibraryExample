package com.sportzinteractive.baseprojectsetup.data.model.auth

sealed class OtpSignInState {
    class Success(val message: String) : OtpSignInState()
    class IncompleteProfile(val message: String) : OtpSignInState()
    class Failure(val message: String) : OtpSignInState()
}

sealed class UpdateUserProfileState {
    class Success(val user:User) : UpdateUserProfileState()
    class Failure(val message: String) : OtpSignInState()
}