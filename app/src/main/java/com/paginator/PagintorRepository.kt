package com.paginator

class PagintorRepository(private val paginatorDataStore: PaginatorDataStore = PaginatorDataStore()) {

    suspend fun loadItems(page:Int,itemsNo:Int):List<Number>{
        return paginatorDataStore.loadItems(page, itemsNo)
    }
}