package com.sportzinteractive.baseprojectsetup.data.model.auth

sealed class OtpSignInState {
    class Success(val message: String,val mobileOtpVerified:Boolean) : OtpSignInState()
    class IncompleteProfile(val message: String,val mobileOtpVerified:Boolean) : OtpSignInState()
    class Failure(val message: String) : OtpSignInState()
}