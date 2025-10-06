package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.cessup.alebrije_multiplatform_kotlin.cache.Database

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = Database.Schema.synchronous(), name ="Database.db")
    }
}