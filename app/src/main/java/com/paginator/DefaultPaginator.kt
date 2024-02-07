package com.paginator

class DefaultPaginator(
    private var currentKey: Int,
    val onRequest: suspend (currentkey: Int) -> List<Number>,
    val onSuccess: (List<Number>) -> Unit,
    val onError: (Throwable) -> Unit,
    val getNextKey: (currentKey: Int) -> Int
) : Paginator {
    private var isLoading: Boolean = false

    override suspend fun loadNextItems() {
        if (isLoading)
            return

        try {
            isLoading = true
            val numbers = onRequest(currentKey)
            onSuccess(numbers)
            currentKey = getNextKey(currentKey)
        } catch (e: Exception) {
            onError(e)
        }
        isLoading = false
    }

    override fun reset() {

    }
}