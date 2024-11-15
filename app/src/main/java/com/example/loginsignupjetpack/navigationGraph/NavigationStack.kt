package com.example.loginsignupjetpack.navigationGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginsignupjetpack.app.LoginApp
import com.example.loginsignupjetpack.screens.LoginScreen
import com.example.loginsignupjetpack.screens.SignUpScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Main.route) {
            LoginApp(navController = navController)
        }
        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.SignUp.route,
        ) {
            SignUpScreen(navController)
        }
    }
}