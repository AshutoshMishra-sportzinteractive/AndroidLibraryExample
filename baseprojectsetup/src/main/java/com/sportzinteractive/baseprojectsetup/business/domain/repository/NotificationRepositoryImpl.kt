package com.sportzinteractive.baseprojectsetup.business.domain.repository

import com.sportzinteractive.baseprojectsetup.business.domain.AuthApiStatus
import com.sportzinteractive.baseprojectsetup.business.domain.GenericStatusCode
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationRequest
import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationState
import com.sportzinteractive.baseprojectsetup.data.repository.NotificationRepository
import com.sportzinteractive.baseprojectsetup.data.services.NotificationService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResult
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ViewModelScoped
class NotificationRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val notificationService: NotificationService,
    private val baseInfo: BaseInfo,
) : NotificationRepository {

    override fun notification(
        url: String,
        notificationRequest: NotificationRequest
    ): Flow<NotificationState> {

        return flow {
            val result = safeApiCall(dispatcher){
                notificationService.subscribeNotification(
                    baseInfo.getNotificationAuthKey(),
                    url, notificationRequest
                )
            }
            when (result) {
                is ApiResult.GenericError -> {
                    emit(NotificationState.Failure(result.message ?: ""))
                }
                is ApiResult.NetworkError -> emit(NotificationState.Failure(result.message ?: ""))
                is ApiResult.Success -> {
                    if (result.data?.status == GenericStatusCode.SUCCESS.statusCode) {
                        if (result.data.content?.status == AuthApiStatus.SUCCESS.statusCode) {
                            if (notificationRequest.data.pref.isNotEmpty()){
                                emit(NotificationState.Success(true))
                            }else{
                                emit(NotificationState.Success(false))
                            }
                        } else {
                            emit(NotificationState.Failure(result.data.content?.msg ?: ""))
                        }
                    } else {
                        emit(NotificationState.Failure(result.data?.status.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}