package com.zenefit.zenefit_android.data.remote.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zenefit.zenefit_android.data.remote.response.ResponseUserPolicyData
import com.zenefit.zenefit_android.data.remote.service.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserInterestPolicyPagingSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val service : UserService): PagingSource<Int, ResponseUserPolicyData.ResultUserPolicyData>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseUserPolicyData.ResultUserPolicyData>): Int? {
        return state.anchorPosition?.let { it ->
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseUserPolicyData.ResultUserPolicyData> {
        return try {
            val page = params.key ?: 0

            val response = withContext(ioDispatcher) {
                service.requestInterestPolicyData(page, 10)
            }

            val responseComments = response.result.content

            val prevKey = if(page == 0) null else page - 1
            val nextKey = if(responseComments.isEmpty()) null else page + 1
            LoadResult.Page(
                data = responseComments,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception : Exception) {
            return LoadResult.Error(exception)
        }
    }
}