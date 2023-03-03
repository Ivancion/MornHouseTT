package com.example.mornhousett.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mornhousett.data.repository.NumberRepository
import com.example.mornhousett.model.Fact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NumberRepository
): ViewModel() {

    private val _fact = MutableStateFlow<Fact?>(null)
    val fact = _fact.asStateFlow()

    fun getFactById(id: Int) {
        viewModelScope.launch {
            val fact = repository.getFactById(id)
            _fact.value = fact
        }
    }
}