package com.tei.harrypottercharacter.data.network

sealed class NetworkUIState<out T> {
    object Loading : NetworkUIState<Nothing>()
    data class Success<out T>(val data: T) : NetworkUIState<T>()
    data class Error(val exception: Throwable) : NetworkUIState<Nothing>()
}