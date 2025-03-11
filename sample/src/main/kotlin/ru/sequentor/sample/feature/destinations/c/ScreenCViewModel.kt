package ru.sequentor.sample.feature.destinations.c

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.destinations.d.DestinationD
import javax.inject.Inject

internal data class ScreenCState(val counter: Int = 0)

@HiltViewModel
internal class ScreenCViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScreenCState())
    val state: StateFlow<ScreenCState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun makeDScreenRoot() {
        router.newRoot(DestinationD())
    }

    fun replaceCScreenToDScreen() {
        router.replace(DestinationD())
    }

    fun onBackClick() {
        router.back()
    }
}