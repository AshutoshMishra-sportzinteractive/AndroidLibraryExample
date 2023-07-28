package com.sportzinteractive.baseprojectsetup.business.domain

enum class AuthApiStatus(val statusCode: Int) {
    SUCCESS(1),

    INCOMPLETE_USER_DATA(2),

    DELETE_REQUEST_ACCOUNT(500),

    INVALID_DATA(0),

    ERROR(-1),

    // User Registered.But Verification Email Not Sent
    REGISTERED_BUT_VERIFICATION_MAIL_NOT_SENT(3),

    //Password Reset link has been sent to your registered email. Kindly check your inbox for further details. The link is valid up to 24 hrs.
    RESET_LINK_SENT(4),

    //Status:6 -->Password reset token has expired. Please raise another request. or Invalid token. Please check the link and try again.
    RESET_PASS_TOKEN_EXPIRED(6),

    //Status: 403 --> Login session expired or invalid login details.In this case please logout the user.
    LOGIN_SESSION_EXPIRED_403(403),

    //Status: 401 --> Login session expired or invalid login details.In this case please logout the user.
    LOGIN_SESSION_EXPIRED_401(401)

}