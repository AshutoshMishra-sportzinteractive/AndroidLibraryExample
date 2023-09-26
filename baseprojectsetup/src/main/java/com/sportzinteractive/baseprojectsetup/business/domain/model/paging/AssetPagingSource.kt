package com.sportzinteractive.baseprojectsetup.business.domain.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

abstract class AssetPagingSource() : PagingSource<Int, AssetItem>() {

    override fun getRefreshKey(state: PagingState<Int, AssetItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AssetItem> {
        val currentPageNo = params.key ?: 1
        val resource = fetchAssetItems(currentPageNo, 10)
            .first { it !is Resource.Loading }
        return if (resource is Resource.Error) {
            loadingDialog(false)
            LoadResult.Error(throwable = resource.throwable)
        } else {
            loadingDialog(false)
            val data = resource.data.orEmpty()

            LoadResult.Page(
                data = data,
                prevKey = null ,
                nextKey = if (data.size < 10) null else currentPageNo + 1
                //passing null when we don't need to make next api call,
                //when data.size is less than the page size that mean, there is no more data
            )
        }
    }

    abstract fun fetchAssetItems(pageNo: Int, pageSize: Int): Flow<Resource<List<AssetItem>?>>
    abstract fun loadingDialog(isShow:Boolean)

}