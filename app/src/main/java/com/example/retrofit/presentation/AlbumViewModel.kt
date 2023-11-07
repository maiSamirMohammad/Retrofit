package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.PhotoAPIState
import com.example.retrofit.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumViewModel (private val repository: Repository): ViewModel(){
    private var _photos= MutableStateFlow<PhotoAPIState>(PhotoAPIState.Loading)
    val photos= _photos.asStateFlow()
    fun getPhotos()
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.getPhotos().catch { throwable->
                _photos.value= PhotoAPIState.Failure(throwable)
            }.collectLatest{ photos->
                _photos.value= PhotoAPIState.Success(photos)
            }
        }
    }
}