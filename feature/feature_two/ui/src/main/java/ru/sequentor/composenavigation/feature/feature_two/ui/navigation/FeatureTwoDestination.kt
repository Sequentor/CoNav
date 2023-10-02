package ru.sequentor.composenavigation.feature.feature_two.ui.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_two.ui.FeatureTwoRoute

@Parcelize
class FeatureTwoDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureTwoRoute() }
}
