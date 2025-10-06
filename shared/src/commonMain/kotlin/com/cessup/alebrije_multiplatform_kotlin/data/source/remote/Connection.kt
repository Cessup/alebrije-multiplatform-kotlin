package com.cessup.alebrije_multiplatform_kotlin.data.source.remote

interface Connection {
    val url: String
    val port: String
}

expect fun getConnection(): Connection