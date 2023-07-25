package com.contlo.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contlo.app.common.toUiModel
import com.contlo.app.data.model.ApiResponse
import com.contlo.app.data.source.PullRequestsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PullRequestsRepository) :
    ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: List<PRUiModel>) : UiState()
        object Error : UiState()
    }

    private val _prUiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val prUiState: StateFlow<UiState> = _prUiState.asStateFlow()

    fun loadPullRequests() {
        viewModelScope.launch {
            _prUiState.value = UiState.Loading
            val data = repository.loadAllPRs()
            if (data is ApiResponse.Success) {
                _prUiState.value =
                    UiState.Success(data.data.map { it.toUiModel() })
            } else {
                _prUiState.value = UiState.Error
            }
        }
    }
}