package com.shaun.sqdelightnotesapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_1
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_1_25
import com.shaun.sqdelightnotesapp.utils.Constants

@Composable
fun TopBar(
    onColorChange: () -> Unit,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    TopAppBar(navigationIcon = {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.clickable(onClick = onClick),
            tint = Color.White
        )
    }, actions = {
        Box(contentAlignment = Alignment.CenterEnd) {
            Icon(
                imageVector = Icons.Filled.Palette,
                contentDescription = "Back",
                modifier = Modifier.clickable(onClick = onColorChange),
                tint = Color.White
            )
        }
    }, title = {

    }, backgroundColor = backgroundColor,
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
    backgroundColor: Color,
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
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = backgroundColor,
            unfocusedLabelColor = backgroundColor,
            textColor = Color.White,
            cursorColor = Color.White,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = backgroundColor,
            unfocusedIndicatorColor = backgroundColor,
            placeholderColor = Color.White.copy(alpha = 0.5f),
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp
        ),
        modifier = Modifier.fillMaxWidth()

    )
}


@Composable
fun AddNotesUi(
    onBack: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    body: String,
    onColorChange: () -> Unit,
    backgroundColor: Long,
    onBodyChange: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(backgroundColor))
    ) {
        TopBar(
            onClick = onBack,
            onColorChange = onColorChange,
            backgroundColor = Color(backgroundColor)
        )
        TitleTextField(
            value = title, onValueChange = onTitleChange,
            fontSize = 20,
            limitedCharacter = true,
            placeHolder = "Title",
            backgroundColor = Color(backgroundColor)
        )

        TitleTextField(
            value = body, onValueChange = onBodyChange,
            fontSize = 15,
            limitedCharacter = false,
            placeHolder = "Note",
            backgroundColor = Color(backgroundColor)

        )

    }
}


@ExperimentalMaterialApi
@Composable
fun ColorBottomSheet(selectedColor: Long, onSelect: (Long) -> Unit) {
    Surface(color = Color(selectedColor)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(selectedColor))
                .padding(grid_1)
        ) {
            Text(text = "Color", color = Color.White)
            Spacer(modifier = Modifier.height(grid_1_25))
            LazyRow(
                content = {


                    items(Constants.colors) { item ->
                        RoundShape(isSelected = item == selectedColor, color = Color(item)) {
                            onSelect(item)
                        }

                        Spacer(modifier = Modifier.width(grid_1_25))
                    }


                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun RoundShape(
    isSelected: Boolean = true,
    color: Color = Color.White,
    onColorChange: () -> Unit
) {

    Card(
        shape = CircleShape,
        backgroundColor = color,
        border = BorderStroke(
            if (isSelected) 1.dp else 0.dp, color = Color.White
        ),
        onClick = {
            onColorChange()
        },
        modifier = Modifier.size(32.dp)
    ) {
        if (isSelected) {
            Icon(imageVector = Icons.Filled.Done, contentDescription = "", tint = Color.White)
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun Test() {
    ColorBottomSheet(selectedColor = Constants.colors[1], onSelect = {})
}

@Preview
@Composable
fun AddNotesScreenPreview() {
    AddNotesUi(
        onBack = { /*TODO*/ },
        title = "Hello",
        onTitleChange = {},
        body = "",
        onBodyChange = {}, onColorChange = {}, backgroundColor = 0xff000000
    )

}