package com.cessup.alebrije_multiplatform_kotlin

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.cessup.alebrije_multiplatform_kotlin.navigation.AppNavHost
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        AppNavHost()
    }
}