package com.example.ghii_training.ui

sealed class Screen(val route: String){
    object MainScreen: Screen("home")
    object DetailScreen: Screen("user_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
