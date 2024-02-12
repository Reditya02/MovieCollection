package com.example.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object ListMovie: Screen("listMovie/{data}") {
        fun createRoute(data: String) = "listMovie/$data"
    }
    object DetailMovie: Screen("detailMovie/{id}") {
        fun createRoute(id: Int) = "detailMovie/$id"
    }
}