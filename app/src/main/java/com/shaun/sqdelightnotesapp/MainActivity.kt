package com.shaun.sqdelightnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.InternalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaun.sqdelightnotesapp.navigation.Routes
import com.shaun.sqdelightnotesapp.presentation.components.ExplodingFab
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainNavHost
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainViewModel
import com.shaun.sqdelightnotesapp.ui.theme.SQDelightNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @InternalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()

        setContent {
            val navController: NavHostController = rememberNavController()
            SQDelightNotesAppTheme {


                Scaffold(
                    floatingActionButton = {

                        ExplodingFab(onClick = {
                        }, transitioned = {
                            navController.navigate(Routes.AddNote.route)

                        }) {

                        }
                    }
                ) {
                    MainNavHost(mainViewModel, navController = navController)
                }
            }
        }
    }


}
