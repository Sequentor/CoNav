package ru.sequentor.sample.feature.bottom.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.sequentor.conav.router.Router
import ru.sequentor.sample.feature.destinations.a.DestinationA
import ru.sequentor.sample.feature.destinations.b.DestinationB
import javax.inject.Inject

internal data class HomeState(val counter: Int = 0)

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _uiState

    fun increase() {
        _uiState.update { it.copy(counter = it.counter + 1) }
    }

    fun decrease() {
        _uiState.update { it.copy(counter = it.counter - 1) }
    }

    fun onNavigateScreenA() {
        router.navigateTo(DestinationA)
    }

    fun onNavigateScreenB() {
        val counter: Int = _uiState.value.counter
        router.navigateTo(DestinationB(counter = counter))
    }
}