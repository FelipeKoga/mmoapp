package com.koga.mmoapp.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koga.mmoapp.data.repository.GameRepository
import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val response = repository.fetchGamesStream()) {
                is Resource.Error -> {
                    println("#### ${response.throwable}")
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(games = response.data)
                    }
                }
            }

        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = true,
    val games: List<Game> = listOf(),
)