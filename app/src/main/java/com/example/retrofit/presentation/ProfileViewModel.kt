package com.example.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.AlbumAPIState
import com.example.retrofit.domain.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(private val repository: IRepository):ViewModel(){
    private var _albums= MutableStateFlow<AlbumAPIState>(AlbumAPIState.Loading)
    val albums= _albums.asStateFlow()
     fun getAlbums()
     {
         viewModelScope.launch(Dispatchers.IO){
             repository.getAlbums().catch { throwable->
                 _albums.value=AlbumAPIState.Failure(throwable)
             }.collectLatest{ albums->
                 _albums.value=AlbumAPIState.Success(albums)
             }
         }
     }
}