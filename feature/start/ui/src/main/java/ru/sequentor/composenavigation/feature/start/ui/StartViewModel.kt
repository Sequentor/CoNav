package ru.sequentor.composenavigation.feature.start.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.core.navigation.router.Router
import ru.sequentor.composenavigation.feature.feature_four.ui.navigation.FeatureFourDestination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.navigation.FeatureOneFirstDestination
import ru.sequentor.composenavigation.feature.feature_three.ui.navigation.FeatureThreeDestination
import ru.sequentor.composenavigation.feature.feature_two.ui.navigation.FeatureTwoDestination
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _startViewState = MutableStateFlow(StartViewState())
    val startViewState: StateFlow<StartViewState> = _startViewState

    fun onNavigationBarClick(route: String) {
        router.popUpTo(route.mapNavigationItemToDestination())
    }

    private fun String.mapNavigationItemToDestination(): Destination = when {
        this == FeatureOneFirstDestination::class.toString() -> FeatureOneFirstDestination()
        this == FeatureTwoDestination::class.toString() -> FeatureTwoDestination()
        this == FeatureThreeDestination::class.toString() -> FeatureThreeDestination()
        this == FeatureFourDestination::class.toString() -> FeatureFourDestination()
        else -> error("wat")
    }
}
