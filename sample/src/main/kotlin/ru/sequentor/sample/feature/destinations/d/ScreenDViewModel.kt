package ru.sequentor.sample.feature.destinations.d

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.destinations.e.DestinationE
import javax.inject.Inject

internal data class ScreenDState(val counter: Int = 0)

@HiltViewModel
internal class ScreenDViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenDState())
    val state: StateFlow<ScreenDState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun onNavigateScreenE() {
        router.navigateTo(DestinationE())
    }

    fun onBackClick() {
        router.back()
    }
}