package com.shaun.sqdelightnotesapp.presentation.screens.addnotes

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shaun.sqdelightnotesapp.presentation.components.AddNotesUi
import com.shaun.sqdelightnotesapp.presentation.components.ColorBottomSheet
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AddNotes(mainViewModel: MainViewModel, onBackPress: () -> Unit) {
     val title by mainViewModel.title
    val body by mainViewModel.body
    val color by mainViewModel.selectedColor
    val context = LocalContext.current

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    BackHandler(enabled = true, onBack = {
        if (body.isNotEmpty()) {
            mainViewModel.addNotes(title = title, color = color, body = body)
        } else {
            Toast.makeText(context, "Empty notes discarded", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.setColor(0xff202124)

        onBackPress()
    })


    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            ColorBottomSheet(selectedColor = color, onSelect = {

                mainViewModel.setColor(it)
            })
        }) {
        AddNotesUi(
            onBack = onBackPress,
            title = title,
            onTitleChange = mainViewModel::setTitle,
            body = body,
            onBodyChange = mainViewModel::setBody,
            onColorChange = {
                scope.launch {
                    println("Hello")
                    state.show()

                }
            },
            backgroundColor = color
        )
    }


}




