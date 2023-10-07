package ru.sequentor.sample.feature.feature_one.second.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.feature.feature_one.second.FeatureOneSecondRoute

@Parcelize
data class FeatureOneSecondDestination(
    val count: Int,
    val result: Result<String>,
) : Destination() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureOneSecondRoute() }
}
