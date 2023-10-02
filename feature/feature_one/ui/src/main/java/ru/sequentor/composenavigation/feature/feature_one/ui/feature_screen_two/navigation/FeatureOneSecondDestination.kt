package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_two.FeatureOneSecondRoute

@Parcelize
data class FeatureOneSecondDestination(
    val count: Int,
    val result: Result<String>,
) : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureOneSecondRoute() }
}
