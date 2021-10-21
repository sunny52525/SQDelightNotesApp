package com.shaun.sqdelightnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.InternalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaun.sqdelightnotesapp.navigation.Routes
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainNavHost
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainViewModel
import com.shaun.sqdelightnotesapp.ui.theme.Dimens.grid_2
import com.shaun.sqdelightnotesapp.ui.theme.KeepLightGray
import com.shaun.sqdelightnotesapp.ui.theme.SQDelightNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@InternalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()


        setContent {
            val navController: NavHostController = rememberNavController()
            SQDelightNotesAppTheme {

                val isVisible by mainViewModel.isInAddNote

                Scaffold(
                    floatingActionButton = {
                        if (isVisible) {
                            FloatingActionButton(
                                onClick = {
                                    mainViewModel.setVisibilityFab(false)
                                    navController.navigate(Routes.AddNote.route)
                                },
                                shape = RoundedCornerShape(grid_2),
                                backgroundColor = KeepLightGray,

                                ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Fab",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                ) {
                    MainNavHost(mainViewModel, navController = navController)
                }
            }
        }
    }


}
