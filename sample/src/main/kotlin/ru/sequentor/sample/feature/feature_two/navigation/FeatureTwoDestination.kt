package ru.sequentor.sample.feature.feature_two.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.feature.feature_two.FeatureTwoRoute

@Parcelize
class FeatureTwoDestination : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureTwoRoute() }
}
