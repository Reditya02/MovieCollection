package com.example.moviecollection.core.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object ListMovie: Screen("home/{data}") {
        fun createRoute(data: String) = "home/$data"
    }
}