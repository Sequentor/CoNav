package ru.sequentor.sample.feature.bottom.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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

class HomeDestination : Destination {
    @Composable
    override fun Content() {
        HomeScreen()
    }
}

@Composable
internal fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { homeViewModel.decrease() }) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(modifier = Modifier.width(32.dp), text = state.counter.toString(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { homeViewModel.increase() }) {
                Text("+")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { homeViewModel.onNavigateScreenA() }) {
            Text("Go to Screen A")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { homeViewModel.onNavigateScreenB() }) {
            Text("Go to Screen B")
        }
    }
}