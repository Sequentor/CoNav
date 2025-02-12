package ru.sequentor.sample.feature.destinations.a

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.destinations.b.DestinationB
import javax.inject.Inject

internal data class ScreenAState(val counter: Int = 0)

@HiltViewModel
internal class ScreenAViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenAState())
    val state: StateFlow<ScreenAState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun onNavigateScreenB() {
        val counter: Int = _uiState.value.counter
        router.navigateTo(DestinationB(counter = counter))
    }

    fun onBackClick() {
        router.back()
    }
}