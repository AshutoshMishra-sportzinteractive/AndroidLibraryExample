package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.model.details.ArticleDetail
import com.sportzinteractive.baseprojectsetup.data.repository.DetailsRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class NewsDetails @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    operator fun invoke(url: String): Flow<Resource<ArticleDetail?>> {
        return detailsRepository.getArticleDetails(url)
    }
}