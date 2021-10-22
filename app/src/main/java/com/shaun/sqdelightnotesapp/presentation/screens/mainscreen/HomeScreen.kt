package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shaun.sqdelightnotesapp.Notes
import com.shaun.sqdelightnotesapp.presentation.components.NotesItem
import com.shaun.sqdelightnotesapp.presentation.components.StaggeredVerticalGrid
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_0_5
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_1
import com.shaun.sqdelightnotesapp.ui.theme.KeepGray

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(mainViewModel: MainViewModel,onNotesClick:(Notes)->Unit) {


    val notes: List<Notes> by mainViewModel.getNotes().collectAsState(listOf())


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(KeepGray)
    ) {
        StaggeredVerticalGrid(maxColumnWidth = 300.dp) {
            notes.forEach {
                Column(modifier = Modifier.padding(grid_0_5)) {
                    NotesItem(body = it.body, color = it.background_color) {
                        onNotesClick(it)
                    }
                }

            }
        }

    }
}