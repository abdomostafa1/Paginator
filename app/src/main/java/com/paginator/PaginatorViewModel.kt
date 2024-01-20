package com.paginator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class PaginatorViewModel(val repository: PagintorRepository = PagintorRepository()) : ViewModel() {

    private val initialKey: Int = 0

    val defaultPaginator = DefaultPaginator(
        currentKey = initialKey,
        onRequest = { currentKey ->
            _mainUiState.emit(MainUiState(isLoading = true))
            repository.loadItems(currentKey, 20)
        },
        

        )

    private val _mainUiState = MutableStateFlow(MainUiState(isLoading = false, items = emptyList()))

    val mainUiState = _mainUiState.asStateFlow()
}
