package com.cessup.alebrije_multiplatform_kotlin.data.source.remote

class IOSConnection() : Connection {
    override val url: String = "localhost"
    override val port: String ="8080"
}

actual fun getConnection(): Connection = IOSConnection()