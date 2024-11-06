package com.devtamuno.composeexerecise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devtamuno.composeexerecise.ui.screen.AppScreen
import com.devtamuno.composeexerecise.ui.theme.ComposeExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { ComposeExerciseTheme { AppScreen() } }
    }
}
