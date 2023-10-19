package ru.sequentor.sample.feature.start

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sequentor.conav.screen.Screen
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.feature_four.navigation.FeatureFourScreen
import ru.sequentor.sample.feature.feature_one.first.navigation.FeatureOneFirstScreen
import ru.sequentor.sample.feature.feature_three.navigation.FeatureThreeScreen
import ru.sequentor.sample.feature.feature_two.navigation.FeatureTwoScreen
import ru.sequentor.sample.feature.start.state.StartViewState
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _startViewState = MutableStateFlow(StartViewState())
    val startViewState: StateFlow<StartViewState> = _startViewState

    fun onNavigationBarClick(route: String) {
        router.popUpTo(route.mapNavigationItemToScreen())
    }

    private fun String.mapNavigationItemToScreen(): Screen = when {
        this == FeatureOneFirstScreen::class.java.canonicalName -> FeatureOneFirstScreen()
        this == FeatureTwoScreen::class.java.canonicalName -> FeatureTwoScreen()
        this == FeatureThreeScreen::class.java.canonicalName -> FeatureThreeScreen()
        this == FeatureFourScreen::class.java.canonicalName -> FeatureFourScreen()
        else -> error("wat")
    }
}
