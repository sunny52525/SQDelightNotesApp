package com.shaun.sqdelightnotesapp.presentation.screens.addnotes

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.shaun.sqdelightnotesapp.presentation.components.AddNotesUi
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainViewModel

@Composable
fun AddNotes(mainViewModel: MainViewModel, onBackPress: () -> Unit) {

    val title by mainViewModel.title
    val body by mainViewModel.body
    val color by mainViewModel.selectedColor
    val context = LocalContext.current


    BackHandler(enabled = true, onBack = {
        if (body.isNotEmpty()) {
            mainViewModel.addNotes(title = title, color = color, body = body)
        } else {
            Toast.makeText(context, "Empty notes discarded", Toast.LENGTH_SHORT).show()
        }
        onBackPress()
    })

    AddNotesUi(
        onBack = onBackPress,
        title = title,
        onTitleChange = mainViewModel::setTitle,
        body = body,
        onBodyChange = mainViewModel::setBody
    )

}




