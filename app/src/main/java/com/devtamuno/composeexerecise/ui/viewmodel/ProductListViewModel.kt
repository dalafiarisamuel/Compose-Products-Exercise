package com.devtamuno.composeexerecise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.devtamuno.composeexerecise.remote.repository.ProductsRemoteRepository
import com.devtamuno.composeexerecise.ui.data.mapper.ProductsMapper
import com.devtamuno.composeexerecise.ui.data.source.ProductsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRemoteRepository,
    private val productsMapper: ProductsMapper,
) : ViewModel() {


    private fun getProductsList() = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ProductsPagingSource(
                repository = repository,
                productsMapper = productsMapper,
            )
        }
    ).flow


    val products = getProductsList().cachedIn(viewModelScope)
}