@file:OptIn(ExperimentalMaterial3Api::class)

package ru.sequentor.sample.feature.destinations.d

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.sequentor.conav.destination.Destination
import ru.sequentor.sample.ui.component.ComposeNavigationTopAppBar
import ru.sequentor.sample.ui.component.NavigationIcon

class DestinationD : Destination {
    @Composable
    override fun Content() {
        ScreenD()
    }
}

@Composable
internal fun ScreenD(
    screenDViewModel: ScreenDViewModel = hiltViewModel(),
) {
    val state by screenDViewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ComposeNavigationTopAppBar(
            title = "Screen D",
            navigationIcon = NavigationIcon.Back,
            onNavigationClick = { screenDViewModel.onBackClick() },
            actions = { /* no-op */ }
        )
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Screen D")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { screenDViewModel.decrease() }) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(modifier = Modifier.width(32.dp), text = state.counter.toString(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { screenDViewModel.increase() }) {
                    Text("+")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { screenDViewModel.onNavigateScreenE() }) {
                Text("Go to Screen E")
            }
        }
    }
}