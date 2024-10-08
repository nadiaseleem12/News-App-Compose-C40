package com.example.news_compose_c40.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(initialLanguage: String):ViewModel(){
    private val _SelectedLanguage = mutableStateOf(initialLanguage)
    val SelectedLanguage :String get() = _SelectedLanguage.value

    fun selectedLanguage(language: String){
        _SelectedLanguage.value=language
    }

    private val _isExpanded = mutableStateOf(false)
    val isExpanded :Boolean get() = _isExpanded.value

    fun setIsExpanded(isExpanded:Boolean){
        _isExpanded.value=isExpanded
    }
}