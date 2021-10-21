package com.shaun.sqdelightnotesapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_1
import com.shaun.sqdelightnotesapp.ui.theme.Gray200
import com.shaun.sqdelightnotesapp.ui.theme.KeepGray

@Composable
fun TopBar(onClick: () -> Unit) {
    TopAppBar(navigationIcon = {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.clickable(onClick = onClick),
            tint = Color.White
        )
    }, actions = {

    }, title = {

    }, backgroundColor = Gray200,
        modifier = Modifier.padding(horizontal = grid_1)
    )
}


@Composable
fun TitleTextField(
    fontSize: Int,
    limitedCharacter: Boolean = false,
    value: String,
    placeHolder: String,
    onValueChange: (String) -> Unit,
) {
    val context = LocalContext.current

    TextField(
        value = value,
        onValueChange = {
            if (limitedCharacter && it.length > 1000) {
                Toast.makeText(context, "Too Large title", Toast.LENGTH_SHORT).show()
            } else {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(text = placeHolder, color = Color.White.copy(alpha = 0.5f))
        },
        colors = textFieldValues(),
        textStyle = TextStyle(
            fontSize = fontSize.sp
        ),
        modifier = Modifier.fillMaxWidth()

    )
}

@Composable
fun textFieldValues(): TextFieldColors = TextFieldDefaults.textFieldColors(
    focusedLabelColor = KeepGray,
    unfocusedLabelColor = KeepGray,
    textColor = Color.White,
    cursorColor = Color.White,
    backgroundColor = KeepGray,
    focusedIndicatorColor = KeepGray,
    unfocusedIndicatorColor = KeepGray,
    placeholderColor = Color.White.copy(alpha = 0.5f),
)


@Composable
fun AddNotesUi(
    onBack: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    body: String,
    onBodyChange: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Gray200)
    ) {
        TopBar(onClick = onBack)
        TitleTextField(
            value = title, onValueChange = onTitleChange,
            fontSize = 20,
            limitedCharacter = true,
            placeHolder = "Title"
        )

        TitleTextField(
            value = body, onValueChange = onBodyChange,
            fontSize = 15,
            limitedCharacter = false,
            placeHolder = "Note"
        )

    }
}


@Preview
@Composable
fun AddNotesScreenPreview() {
    AddNotesUi(
        onBack = { /*TODO*/ },
        title = "Hello",
        onTitleChange = {},
        body = "",
        onBodyChange = {}
    )

}