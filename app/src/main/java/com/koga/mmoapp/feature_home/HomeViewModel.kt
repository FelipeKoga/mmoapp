package com.koga.mmoapp.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koga.mmoapp.data.repository.GameRepository
import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        repository.fetchGamesStream().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true, games = resource.data ?: it.games)
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, games = resource.data ?: it.games)
                    }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, games = resource.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val games: List<Game> = listOf(),
)