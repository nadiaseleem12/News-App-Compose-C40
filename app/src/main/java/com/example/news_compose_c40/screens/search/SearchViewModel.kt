package com.example.news_compose_c40.screens.search
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {
    private val _SearchQuery = mutableStateOf("")
    val SearchQuery :String get() = _SearchQuery.value

    fun setSearchQuery(query: String){
        _SearchQuery.value=query
    }

    private val _isfocused = mutableStateOf(false)
    val isfocused :Boolean get() = _isfocused.value

    fun setIsFocused (isfocused:Boolean){
        _isfocused.value=isfocused
    }

}