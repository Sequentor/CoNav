package ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sequentor.composenavigation.core.ui.component.ComposeNavigationTopAppBar
import ru.sequentor.composenavigation.core.ui.component.NavigationIcon
import ru.sequentor.composenavigation.feature.feature_one.ui.R
import ru.sequentor.composenavigation.feature.feature_one.ui.feature_screen_one.state.FeatureOneViewState

@Composable
internal fun FeatureOneFirstRoute(
    modifier: Modifier = Modifier,
    viewModel: FeatureOneViewModel = hiltViewModel(),
) {
    val featureOneViewState: FeatureOneViewState by viewModel.featureOneViewState.collectAsStateWithLifecycle()
    FeatureOneFirstScreen(
        modifier = modifier,
        featureOneViewState = featureOneViewState,
        onButtonClick = viewModel::onButtonClick,
        onNavigateSecondScreen = viewModel::onNavigateSecondScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeatureOneFirstScreen(
    modifier: Modifier = Modifier,
    featureOneViewState: FeatureOneViewState,
    onButtonClick: () -> Unit,
    onNavigateSecondScreen: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ComposeNavigationTopAppBar(
            titleRes = R.string.feature_one,
            navigationIcon = NavigationIcon.None,
            actions = { /* no-op */ }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Counter: ${featureOneViewState.count}")
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onButtonClick) {
                Text("Increase")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onNavigateSecondScreen) {
                Text("Navigate on Second Screen")
            }
        }
    }
}
