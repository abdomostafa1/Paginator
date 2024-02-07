package com.paginator

import android.util.Log
import kotlinx.coroutines.delay

private const val TAG = "PaginatorDataStore"
class PaginatorDataStore {

    private var items = emptyList<Number>()

    init {
        for (i in 1..100) {
            items = items.plus(Number(i, "Number = $i"))
        }
    }


    suspend fun loadItems(page: Int, itemsNo: Int): List<Number> {
        Log.e(TAG, "inside loadItems on datasource class ", )
        delay(2000)
        if (page * itemsNo > items.size)
            return emptyList()

        val fromIndex = page  * itemsNo
        val toIndex = fromIndex + itemsNo
        val subItems = items.subList(fromIndex, toIndex)
        return subItems
    }

}