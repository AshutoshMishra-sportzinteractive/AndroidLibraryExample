package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetNewsListingLayoutBuilder @Inject constructor(
    private val listingRepository: ListingRepository
) {
    operator fun invoke(url:String): Flow<Resource<List<AssetItem>?>> {
        return flow {
            val data = listingRepository.getNewsPageListing(url).first() { it !is Resource.Loading }.data
            val list = data?.firstOrNull()?.let {
                it as NewsListingItem.Listing
                it.items
            }?:run{
                emptyList()
            }
            emit(Resource.Success(list))
        }
    }

}