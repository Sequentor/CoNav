package ru.sequentor.composenavigation.feature.feature_two.ui.navigation

import androidx.compose.runtime.Composable
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_two.ui.FeatureTwoRoute

object FeatureTwoDestination : Destination() {

    override val route: String = "feature_two_route"

    override val screenContent: @Composable () -> Unit = { FeatureTwoRoute() }
}
