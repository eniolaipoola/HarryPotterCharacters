package com.tei.harrypottercharacter.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NetworkUIState<List<CharacterModel>>>(NetworkUIState.Loading)
    val uiState: StateFlow<NetworkUIState<List<CharacterModel>>> = _uiState

    private var fetchJob: Job? = null

    private fun cancelJob() {
        fetchJob?.cancel()
        fetchJob = null
    }

    fun getAllCharacters() {
        fetchJob = viewModelScope.launch {
            try {
                repository.fetchAllCharacters().collect { result ->
                    _uiState.value = result
                }
            } catch (e: Exception) {
                _uiState.value = NetworkUIState.Error(e)
            }
        }
    }

    fun performSearch(searchQuery: String) {
        viewModelScope.launch {
            try {
                repository.searchCharacter(searchQuery).collect { searchResult ->
                    _uiState.value = searchResult
                }
            } catch (e: Exception) {
                _uiState.value = NetworkUIState.Error(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

}