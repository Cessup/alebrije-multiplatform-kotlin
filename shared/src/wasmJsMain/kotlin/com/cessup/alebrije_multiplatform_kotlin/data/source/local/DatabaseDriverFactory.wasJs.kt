package com.cessup.alebrije_multiplatform_kotlin.data.source.local

import app.cash.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        throw NotImplementedError("SQLDelight is not supported in Kotlin/Wasm yet.")
    }
}
