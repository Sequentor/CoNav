package ru.sequentor.sample.feature.bottom.music

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import javax.inject.Inject

internal data class MusicState(val counter: Int = 0)

@HiltViewModel
internal class MusicViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MusicState())
    val state: StateFlow<MusicState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }
}