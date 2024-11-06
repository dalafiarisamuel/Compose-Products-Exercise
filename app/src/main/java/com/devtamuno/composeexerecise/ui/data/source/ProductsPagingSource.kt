package com.devtamuno.composeexerecise.ui.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devtamuno.composeexerecise.remote.repository.ProductsRemoteRepository
import com.devtamuno.composeexerecise.remote.repository.Resource
import com.devtamuno.composeexerecise.ui.data.ProductUi
import com.devtamuno.composeexerecise.ui.data.mapper.ProductsMapper


class ProductsPagingSource(
    private val repository: ProductsRemoteRepository,
    private val productsMapper: ProductsMapper,
) : PagingSource<Int, ProductUi>() {


    override fun getRefreshKey(state: PagingState<Int, ProductUi>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductUi> {

        return when (val result = repository.getAllProducts()) {
            is Resource.Failure -> {
                LoadResult.Error(result.error)
            }

            is Resource.Success -> {
                val products = productsMapper.mapToUi(result.result).productUis
                LoadResult.Page(
                    data = products,
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }
}