package com.shaun.sqdelightnotesapp.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@InternalAnimationApi
fun <S> Transition.Segment<S>.transitioning(pair: Pair<S, S>): Boolean {
    return this.initialState == pair.first && this.targetState == pair.second
}

data class FabState(val fabColor: Color, val fabSize: Float, val fabIconAlpha: Float)

@InternalAnimationApi
@Composable
fun ExplodingFab(
    onClick: () -> Unit,
    transitioned: () -> Unit,
    icon: @Composable () -> Unit
) {
    val primaryColor = MaterialTheme.colors.primary
    val explodedColor = Color.White


    val normalState = FabState(fabColor = primaryColor, fabSize = 0f, fabIconAlpha = 1f)
    val explodedState = FabState(fabColor = explodedColor, fabSize = 2000f, fabIconAlpha = 0f)

    var fabState by remember { mutableStateOf(normalState) }


    val transition = updateTransition(
        targetState = fabState, label = "",
    )


    val fabColor by transition.animateColor(

        transitionSpec = {
            if (transitioning(initialState to normalState)) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500, delayMillis = 300)
            }
        }, label = ""
    ) { it.fabColor }

    val fabSize by transition.animateFloat(
        label = ""
    ) {
        it.fabSize
    }

    val fabIconAlpha by transition.animateFloat(label = "", transitionSpec = {
        keyframes {
            durationMillis = 1000
            1f at 0
            1f at 100
            1f at 200
            1f at 300
            1f at 400
            1f at 500


        }
    }, targetValueByState = {
        it.fabIconAlpha
    })

    Fab(
        backgroundColor = fabColor,
        alpha = fabIconAlpha,
        fabSize = fabSize,
        icon = icon,
        onClick = {
            fabState = if (fabState == normalState) {
                explodedState
            } else {
                normalState
            }

            onClick()
        }
    )


}

@Composable
fun Fab(
    onClick: () -> Unit,
    backgroundColor: Color,
    fabSize: Float,
    alpha: Float,
    icon: @Composable () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .zIndex(20f)
            .alpha(alpha = alpha), contentAlignment = Alignment.BottomEnd
    ) {

        Canvas(modifier = Modifier
            .size(fabSize.dp), onDraw = {
            val width = size.width
            val height = size.height
            drawCircle(
                color = Color.Red,
                radius = fabSize,
                center = Offset(width, height)
            )
        })

        FloatingActionButton(onClick = { onClick() }, modifier = Modifier.alpha(alpha = alpha)) {
            Icon(imageVector = Icons.Default.PlusOne, contentDescription = "")
        }


    }

}