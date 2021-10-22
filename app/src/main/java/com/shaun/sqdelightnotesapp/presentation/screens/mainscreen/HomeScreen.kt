package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shaun.sqdelightnotesapp.Notes
import com.shaun.sqdelightnotesapp.presentation.components.NotesItem
import com.shaun.sqdelightnotesapp.presentation.components.StaggeredVerticalGrid
import com.shaun.sqdelightnotesapp.ui.theme.KeepGray

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(mainViewModel: MainViewModel,onNotesClick:(Notes)->Unit) {


    val notes: List<Notes> by mainViewModel.getNotes().collectAsState(listOf())

    if (notes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No notes added,Click on the plus button to start", color = Color.White)
        }
    } else
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(KeepGray)
        ) {

            StaggeredVerticalGrid(maxColumnWidth = 300.dp) {
                notes.forEach { note ->
                    NotesItem(body = note.body, color = note.background_color) {
                        onNotesClick(note)
                    }
                }
            }
        }


}