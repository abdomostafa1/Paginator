package com.paginator

import kotlinx.coroutines.delay

class PaginatorDataStore {

    private var items = emptyList<Number>()

    init {
        for (i in 1..100) {
            items = items.plus(Number(i, "Number = $i"))
        }
    }


    suspend fun loadItems(page: Int, itemsNo: Int): List<Number> {
        delay(2000)
        if (page * itemsNo > items.size)
            return emptyList()

        val fromIndex = (page - 1) * itemsNo
        val toIndex = fromIndex + itemsNo - 1
        val subItems = items.subList(fromIndex, toIndex)
        return subItems
    }

}