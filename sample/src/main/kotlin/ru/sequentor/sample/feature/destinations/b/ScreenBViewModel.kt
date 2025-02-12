package ru.sequentor.sample.feature.destinations.b

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.screen.args
import ru.sequentor.sample.feature.destinations.c.DestinationC
import javax.inject.Inject

internal data class ScreenBState(val counter: Int = 0)

@HiltViewModel
internal class ScreenBViewModel @Inject constructor(
    private val router: Router,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: DestinationB = savedStateHandle.args()

    private val _uiState = MutableStateFlow(ScreenBState(counter = args.counter))
    val state: StateFlow<ScreenBState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun onNavigateScreenC() {
        router.navigateTo(DestinationC)
    }

    fun onBackClick() {
        router.back()
    }
}