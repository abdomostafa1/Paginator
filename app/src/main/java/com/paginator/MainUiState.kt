package com.paginator

data class MainUiState(val isLoading: Boolean, val items: List<Number>, val errorMessage:String?=null,val isEndReached: Boolean)
