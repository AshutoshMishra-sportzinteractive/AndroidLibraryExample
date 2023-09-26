package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.model.details.PhotoDetails
import com.sportzinteractive.baseprojectsetup.data.repository.DetailsRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class PhotoDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository,

    ) {
    operator fun invoke(url: String): Flow<Resource<PhotoDetails?>> {
        return detailsRepository.getPhotoDetails(url)
    }
}