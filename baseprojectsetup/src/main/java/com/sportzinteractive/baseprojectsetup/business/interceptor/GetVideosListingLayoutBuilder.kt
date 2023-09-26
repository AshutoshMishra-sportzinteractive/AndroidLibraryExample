package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosListingItem
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetVideosListingLayoutBuilder @Inject constructor(
    private val listingRepository: ListingRepository
) {
    operator fun invoke(url:String): Flow<Resource<List<VideosListingItem>?>> {
        return listingRepository.getVideoPageListing(url)
    }
}