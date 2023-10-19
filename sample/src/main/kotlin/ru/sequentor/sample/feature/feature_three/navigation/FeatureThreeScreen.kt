package ru.sequentor.sample.feature.feature_three.navigation

import androidx.compose.runtime.Composable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import ru.sequentor.conav.screen.Screen
import ru.sequentor.sample.feature.feature_three.FeatureThreeRoute

@Parcelize
class FeatureThreeScreen : Screen() {

    @IgnoredOnParcel
    override val screenContent: @Composable () -> Unit = { FeatureThreeRoute() }
}
