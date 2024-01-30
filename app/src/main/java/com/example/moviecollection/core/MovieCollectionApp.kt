package com.example.moviecollection.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviecollection.core.navigation.Screen
import com.example.moviecollection.ui.home.HomeScreen
import com.example.moviecollection.ui.listmovie.ListMovieScreen

@Composable
fun MovieCollectionApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToListMovie = {
                    navController.navigate(Screen.ListMovie.createRoute(it))
                }
            )
        }
        
        composable(
            route = Screen.ListMovie.route,
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) {
            ListMovieScreen(data = it.arguments?.getString("data") ?: "")
        }
    }
}