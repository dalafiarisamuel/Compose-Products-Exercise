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

        val page = params.key ?: 1
        val skip = (page - 1) * PAGE_SIZE

        return when (val result = repository.getAllProducts(limit = PAGE_SIZE, skip)) {
            is Resource.Failure -> {
                LoadResult.Error(result.error)
            }

            is Resource.Success -> {
                val products = productsMapper.mapToUi(result.result).productUis
                LoadResult.Page(
                    data = products,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (products.isEmpty()) null else page + 1
                )
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}