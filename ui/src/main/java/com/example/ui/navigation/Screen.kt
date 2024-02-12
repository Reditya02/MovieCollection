package com.example.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object ListMovie: Screen("listMovie/{data}") {
        fun createRoute(data: String) = "listMovie/$data"
    }
    data object DetailMovie: Screen("detailMovie/{id}") {
        fun createRoute(id: Int) = "detailMovie/$id"
    }
}