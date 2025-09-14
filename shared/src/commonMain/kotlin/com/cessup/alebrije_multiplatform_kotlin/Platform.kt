package com.cessup.alebrije_multiplatform_kotlin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform