package ru.sequentor.sample.feature.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import ru.sequentor.conav.navigator.Navigator
import ru.sequentor.sample.ui.theme.ComposeNavigationTheme
import javax.inject.Inject

@AndroidEntryPoint
internal class StartActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ComposeNavigationTheme {
                Surface {
                    StartScreen(navigator = navigator)
                }
            }
        }
    }
}
