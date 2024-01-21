package com.paginator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class PaginatorViewModel(private val repository: PagintorRepository = PagintorRepository()) :
    ViewModel() {

    private val initialKey: Int = 0

    val defaultPaginator = DefaultPaginator(
        currentKey = initialKey,
        onRequest = { currentKey ->
            _mainUiState.update { it.copy(isLoading = true) }
            repository.loadItems(currentKey, 20)
        },
        onSuccess = { newItems ->
            if (newItems.isEmpty())
                _mainUiState.update { it.copy(isEndReached = true) }

            _mainUiState.update { it.copy(items = it.items + newItems, isLoading = false) }
        },
        onError = { throwable ->
            _mainUiState.update { it.copy(errorMessage = throwable.message) }
        },
        getNextKey = { currentKey ->
            currentKey + 1
        }
    )

    private val _mainUiState =
        MutableStateFlow(MainUiState(isLoading = false, items = emptyList(), isEndReached = false))

    val mainUiState = _mainUiState.asStateFlow()
}
