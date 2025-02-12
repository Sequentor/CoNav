package ru.sequentor.sample.feature.destinations.f

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.conav.screen.route
import ru.sequentor.sample.feature.destinations.b.DestinationB
import javax.inject.Inject

internal data class ScreenFState(val counter: Int = 0)

@HiltViewModel
internal class ScreenFViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenFState())
    val state: StateFlow<ScreenFState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun backToScreenB() {
        router.backTo(route<DestinationB>())
    }

    fun onBackClick() {
        router.back()
    }
}