package ru.sequentor.sample.feature.feature_three.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.feature.feature_three.FeatureThreeRoute

@Parcelize
class FeatureThreeDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureThreeRoute() }
}
