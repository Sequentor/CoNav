package ru.sequentor.sample.feature.start

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sequentor.conav.Navigator
import ru.sequentor.sample.feature.feature_one.first.navigation.FeatureOneFirstDestination
import ru.sequentor.sample.feature.start.state.StartViewState
import ru.sequentor.sample.ui.component.ComposeNavigationNavigationBarCornered
import ru.sequentor.sample.ui.component.ComposeNavigationScaffold
import ru.sequentor.sample.ui.theme.ComposeNavigationTheme

@Composable
internal fun StartScreen(
    navigator: Navigator,
    startViewModel: StartViewModel = hiltViewModel(),
) {
    val startViewState: StartViewState by startViewModel.startViewState.collectAsStateWithLifecycle()
    ComposeNavigationTheme {
        ComposeNavigationScaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                ComposeNavigationNavigationBarCornered(
                    navigationItems = startViewState.navigationItems,
                    onNavigationBarClick = startViewModel::onNavigationBarClick
                )
            },
            content = {
                navigator(startDestination = FeatureOneFirstDestination())
            }
        )
    }
}
