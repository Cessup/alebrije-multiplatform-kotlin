package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import com.cessup.alebrije_multiplatform_kotlin.cache.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    lateinit var database: Database

    fun init(factory: DatabaseDriverFactory) {
        CoroutineScope(Dispatchers.Default).launch {
            val driver = factory.createDriver()
            database = Database(driver)
        }
    }
}