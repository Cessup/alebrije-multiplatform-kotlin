package com.cessup.alebrije_multiplatform_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cessup.alebrije_multiplatform_kotlin.data.source.local.SharedPrefsProvider
import com.cessup.alebrije_multiplatform_kotlin.navigation.AppNavHost
import com.cessup.alebrije_multiplatform_kotlin.ui.theme.AlebrijeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        SharedPrefsProvider.init(this)

        setContent {
            AppNavHost()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AlebrijeTheme {
        AppNavHost()
    }
}