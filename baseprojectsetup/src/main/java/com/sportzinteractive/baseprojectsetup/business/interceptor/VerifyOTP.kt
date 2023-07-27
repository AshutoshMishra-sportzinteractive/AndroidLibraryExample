package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.AuthBaseRequest
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailOTPRequest
import com.sportzinteractive.baseprojectsetup.data.repository.BaseRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class VerifyOTP @Inject constructor(
    private val baseRepository: BaseRepository
) {
    operator fun invoke(
        url: String,
        userEmail: String?,
        mobile: String?,
        countryCode: String?,
        type: Int,
        captcha: String
    ): Flow<Resource<Response<OTPResponse?>>> {
        return flow {
            baseRepository.rawJsonBaseApiCallPost<OTPResponse>(
                AuthBaseRequest(
                    SendEmailOTPRequest(
                        emailId = userEmail ?: "",
                        mobile_no = mobile ?: "",
                        countryCode = countryCode,
                        isApp = "1",
                        type = type,
                        captcha = captcha
                    )
                ),
                url
            )
        }
    }
}
