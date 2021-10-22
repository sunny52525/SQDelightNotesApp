package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {


    private val _body = mutableStateOf<String>("")
    val body: State<String> = _body
    private val _title = mutableStateOf<String>("")
    val title: State<String> = _title

    private val _selectedColor = mutableStateOf(0xff202124)
    val selectedColor: State<Long> = _selectedColor

    private val _isInAddNote = mutableStateOf<Boolean>(true)
    val isInAddNote: State<Boolean> = _isInAddNote


    private val _isInViewingMode = mutableStateOf(false)
    val isInViewingMode: State<Boolean> = _isInViewingMode
    private val _viewingNoteId = mutableStateOf<Long>(0)
    val viewingNoteId: State<Long> = _viewingNoteId

    fun setViewingNoteId(id: Long) {
        _viewingNoteId.value = id
    }




    fun setViewingMode(viewMode: Boolean) {
        _isInViewingMode.value = viewMode
    }

    fun setVisibilityFab(visible: Boolean) {
        _isInAddNote.value = visible
    }

    fun setColor(color: Long) {
        _selectedColor.value = color
    }

    fun setBody(body: String) {
        _body.value = body
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun addNotes(title: String, body: String, color: Long) {
        mainRepository.addNotes(title, body, color)
        setColor(0xff202124)
        setTitle("")
        setBody("")
    }

    fun getNotes() = mainRepository.getNotes()
    fun deleteNote(id: Long) = mainRepository.deleteNote(id)
    fun editNote(title: String, color: Long, body: String, id: Long) {
    mainRepository.editNote(title,color,body,id)
    }
}