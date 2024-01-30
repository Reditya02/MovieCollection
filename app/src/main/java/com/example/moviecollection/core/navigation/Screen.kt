package com.example.moviecollection.core.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object ListMovie: Screen("home/{id}") {
        fun createRoute(id: String) = "home/$id"
    }
}