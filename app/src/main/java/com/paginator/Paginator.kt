package com.paginator

interface Paginator {
    suspend fun loadNextItems()
    fun reset()
}