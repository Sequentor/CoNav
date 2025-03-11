package ru.sequentor.sample.feature.destinations.e

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.destinations.f.DestinationF
import javax.inject.Inject

internal data class ScreenEState(val counter: Int = 0)

@HiltViewModel
internal class ScreenEViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenEState())
    val state: StateFlow<ScreenEState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun onNavigateScreenF() {
        router.navigateTo(DestinationF())
    }

    fun replaceEScreenToFScreen() {
        router.replace(DestinationF())
    }

    fun onBackClick() {
        router.back()
    }
}