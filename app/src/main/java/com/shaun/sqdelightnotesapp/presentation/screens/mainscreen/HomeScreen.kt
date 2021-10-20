package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaun.sqdelightnotesapp.Notes
import com.shaun.sqdelightnotesapp.presentation.components.NotesItem
import com.shaun.sqdelightnotesapp.presentation.components.StaggeredVerticalGrid

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    var note by mainViewModel.note

    val notes: List<Notes> by mainViewModel.getNotes().collectAsState(listOf())

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        StaggeredVerticalGrid(maxColumnWidth = 300.dp) {
            notes.forEach {
                NotesItem(body = it.body, color = it.background_color) {
                    mainViewModel.deleteNote(it.id)
                }

            }
        }

    }
}