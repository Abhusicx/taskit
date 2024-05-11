package com.example.taskit

sealed class Screen (val route: String) {
    object HomeScreen : Screen("Home_Screen")
    object AddScreen : Screen("add_screen")
}