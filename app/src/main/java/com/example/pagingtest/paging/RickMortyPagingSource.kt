package com.example.pagingtest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingtest.api.ApiService
import com.example.pagingtest.model.RickMorty

class RickMortyPagingSource(private val apiService: ApiService) : PagingSource<Int, RickMorty>() {

    override fun getRefreshKey(state: PagingState<Int, RickMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickMorty> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllCharacters(page = currentPage)
            val responseData = mutableListOf<RickMorty>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            LoadResult.Page(data = responseData, prevKey = if (currentPage == 1) null else currentPage - 1, nextKey = currentPage.plus(1))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}