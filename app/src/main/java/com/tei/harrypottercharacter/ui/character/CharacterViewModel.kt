package com.tei.harrypottercharacter.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tei.harrypottercharacter.data.model.CharacterModel
import com.tei.harrypottercharacter.data.network.NetworkUIState
import com.tei.harrypottercharacter.data.repository.CharacterRepository
import com.tei.harrypottercharacter.di.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<NetworkUIState<List<CharacterModel>>>(NetworkUIState.Loading)
    val uiState: StateFlow<NetworkUIState<List<CharacterModel>>> = _uiState

    private val _characterList = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characterList: StateFlow<List<CharacterModel>> get() = _characterList

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

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

}