package com.sportzinteractive.baseprojectsetup.business.domain.model.listing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingEntityData(
    val entities: String,
    val excludeEntities: String,
    val otherEntities: String
): Parcelable
