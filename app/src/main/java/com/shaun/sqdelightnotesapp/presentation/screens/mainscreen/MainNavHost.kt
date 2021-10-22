package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shaun.sqdelightnotesapp.navigation.Routes
import com.shaun.sqdelightnotesapp.presentation.screens.addnotes.AddNotes
import com.shaun.sqdelightnotesapp.ui.theme.KeepGray
import com.shaun.sqdelightnotesapp.ui.theme.KeepLightGray

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainNavHost(mainViewModel: MainViewModel,navController: NavHostController) {

    val systemUiController = rememberSystemUiController()

    val selectedColor by mainViewModel.selectedColor
    DisposableEffect(key1 = selectedColor, effect ={
        onDispose {
            systemUiController.setSystemBarsColor(
                color = Color(selectedColor),
                darkIcons = false
            )
        }
    } )
    NavHost(navController = navController, startDestination = Routes.Home.route, builder = {

        composable(Routes.Home.route) {
            HomeScreen(mainViewModel)
        }
        composable(Routes.AddNote.route) {

            AddNotes(mainViewModel, onBackPress = {
                navController.popBackStack()
                mainViewModel.setVisibilityFab(true)
            })
        }

    }, modifier = Modifier
        .fillMaxSize()
        .background(KeepGray)
    )

}