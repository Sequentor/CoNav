package ru.sequentor.sample.feature.start

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sequentor.conav.destination.Destination
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.feature_four.navigation.FeatureFourDestination
import ru.sequentor.sample.feature.feature_one.first.navigation.FeatureOneFirstDestination
import ru.sequentor.sample.feature.feature_three.navigation.FeatureThreeDestination
import ru.sequentor.sample.feature.feature_two.navigation.FeatureTwoDestination
import ru.sequentor.sample.feature.start.state.StartViewState
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
        this == FeatureOneFirstDestination::class.java.canonicalName -> FeatureOneFirstDestination()
        this == FeatureTwoDestination::class.java.canonicalName -> FeatureTwoDestination()
        this == FeatureThreeDestination::class.java.canonicalName -> FeatureThreeDestination()
        this == FeatureFourDestination::class.java.canonicalName -> FeatureFourDestination()
        else -> error("wat")
    }
}
