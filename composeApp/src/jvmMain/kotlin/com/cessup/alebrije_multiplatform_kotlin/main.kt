package com.cessup.alebrije_multiplatform_kotlin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cessup.alebrije_multiplatform_kotlin.navigation.AppNavHost

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "alebrije_multiplatform_kotlin",
    ) {
        AppNavHost()
    }
}