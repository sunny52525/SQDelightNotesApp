package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaun.sqdelightnotesapp.navigation.Routes

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainNavHost(mainViewModel: MainViewModel,navController: NavHostController) {


    NavHost(navController = navController, startDestination = Routes.Home.route, builder = {

        composable(Routes.Home.route) {
          HomeScreen(mainViewModel)
        }
        composable(Routes.AddNote.route){
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello", color = Color.White)
            }
        }

    })

}