package com.devtamuno.composeexerecise.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devtamuno.composeexerecise.remote.repository.ProductsRemoteRepository
import com.devtamuno.composeexerecise.remote.repository.Resource
import com.devtamuno.composeexerecise.ui.data.mapper.ProductDetailMapper
import com.devtamuno.composeexerecise.ui.navigation.ARG_PRODUCT_ID
import com.devtamuno.composeexerecise.ui.screen.state.ProductDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductsRemoteRepository,
    private val productDetailMapper: ProductDetailMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailState())
    val uiState: StateFlow<ProductDetailState> = _uiState.asStateFlow()

    init {
        val intentProductId = savedStateHandle.get<Int>(ARG_PRODUCT_ID)!!
        getSelectedProductById(intentProductId)
    }


    private fun getSelectedProductById(productId: Int) = viewModelScope.launch {
        when (val result = repository.getProductById(productId)) {
            is Resource.Success -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        productDetail = productDetailMapper.mapToUi(result.result),
                        error = null
                    )
                }
            }
            is Resource.Failure -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.error
                    )
                }
            }
        }

    }
}