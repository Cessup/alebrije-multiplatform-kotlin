package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
