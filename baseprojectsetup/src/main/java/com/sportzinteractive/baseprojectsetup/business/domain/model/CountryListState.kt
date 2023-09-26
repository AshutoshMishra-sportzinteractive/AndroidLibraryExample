package com.sportzinteractive.baseprojectsetup.business.domain.model

import com.sportzinteractive.baseprojectsetup.data.model.country.Country

data class CountryListState(
    val countries: List<Country>?,
    val defaultSelectedPosition: Int = 0
)