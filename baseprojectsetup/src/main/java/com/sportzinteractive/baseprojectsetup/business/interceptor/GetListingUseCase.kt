package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class GetListingUseCase @Inject constructor(
    private val listingRepository: ListingRepository,

    ) {
    operator fun invoke(url: String): Flow<Resource<List<AssetItem>?>> {
        return listingRepository.getEntityListing(url)
    }
}