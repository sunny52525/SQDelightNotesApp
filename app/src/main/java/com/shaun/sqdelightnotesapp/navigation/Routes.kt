package com.shaun.sqdelightnotesapp.navigation

sealed class Routes(val route:String){
    object Home:Routes("Home")
    object AddNote:Routes("add_new")
}