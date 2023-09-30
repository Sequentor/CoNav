package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.navigation

import androidx.compose.runtime.Composable
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.FeatureOneFirstRoute

object FeatureOneFirstDestination : Destination() {

    override val route: String = "feature_one_route"

    override val screenContent: @Composable () -> Unit = { FeatureOneFirstRoute() }
}
