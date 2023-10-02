package ru.sequentor.composenavigation.feature.feature_three.ui.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.composenavigation.core.navigation.destination.Destination
import ru.sequentor.composenavigation.feature.feature_three.ui.FeatureThreeRoute

@Parcelize
class FeatureThreeDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureThreeRoute() }
}

