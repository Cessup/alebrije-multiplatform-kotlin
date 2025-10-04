package com.cessup.alebrije_multiplatform_kotlin.data.source

class AndroidConnection() : Connection {
    override val url: String = "10.0.2.2"
    override val port: String ="8080"
}

actual fun getConnection(): Connection = AndroidConnection()