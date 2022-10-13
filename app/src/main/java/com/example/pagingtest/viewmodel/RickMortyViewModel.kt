package com.example.pagingtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pagingtest.api.ApiService
import com.example.pagingtest.paging.RickMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 20), pagingSourceFactory = {
        RickMortyPagingSource(apiService)
    }).flow.cachedIn(viewModelScope)
}