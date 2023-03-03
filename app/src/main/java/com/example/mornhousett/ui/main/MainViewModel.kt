package com.example.mornhousett.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mornhousett.data.repository.NumberRepository
import com.example.mornhousett.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository
): ViewModel() {

    val facts = repository.observeAllFacts()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    fun getRandomFact() {
        viewModelScope.launch {
            repository.getRandomFact().collect {
                when(it) {
                    is Resource.Success -> Unit
                    is Resource.Error -> {
                        _errorMessage.emit(it.exception)
                    }
                }
            }
        }
    }

    fun getFactByNumber(number: Int) {
        viewModelScope.launch {
            repository.getFactByNumber(number).collect {
                when(it) {
                    is Resource.Success -> Unit
                    is Resource.Error -> {
                        _errorMessage.emit(it.exception)
                    }
                }
            }
        }
    }
}