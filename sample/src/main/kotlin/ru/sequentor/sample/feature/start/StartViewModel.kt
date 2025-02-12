package ru.sequentor.sample.feature.start

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.screen.Destination
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _startViewState = MutableStateFlow(StartViewState())
    val startViewState: StateFlow<StartViewState> = _startViewState

    fun onNavigationBarClick(destination: Destination) {
        router.bottom(destination)
    }
}
