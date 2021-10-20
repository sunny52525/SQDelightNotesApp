package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val note = mutableStateOf("")


    fun addNotes(title: String, body: String, color: Long) {
        mainRepository.addNotes(title, body, color)


    }

    fun getNotes() = mainRepository.getNotes()
    fun deleteNote(id:Long) = mainRepository.deleteNote(id)
}